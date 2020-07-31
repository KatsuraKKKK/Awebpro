package com.tarpitout.aio.jdk.nio;

import java.io.*;
import java.net.*;
import java.nio.*;
import java.nio.channels.*;

/**
 * Created by katsurakkkk on 9/4/16.
 */
public class BufferTest {
	public void channelCopy() throws IOException {
		ReadableByteChannel source = Channels.newChannel(System.in);
		WritableByteChannel dest = Channels.newChannel(System.out);
		doChannelCopy(source, dest);
		source.close();
		dest.close();
	}

	private void doChannelCopy(ReadableByteChannel src, WritableByteChannel dest) throws IOException {
		ByteBuffer buffer = ByteBuffer.allocateDirect(16 * 1024);
		while (src.read(buffer) != -1) {
			buffer.flip();
			dest.write(buffer);
			buffer.compact();
		}
		buffer.flip();
		while (buffer.hasRemaining()) {
			dest.write(buffer);
		}
	}

	private void doChannelCopy1(ReadableByteChannel src, WritableByteChannel dest) throws IOException {
		ByteBuffer buffer = ByteBuffer.allocateDirect(16 * 1024);
		while (src.read(buffer) != -1) {
			buffer.flip();
			while (buffer.hasRemaining()) {
				dest.write(buffer);
			}
			buffer.clear();
		}
	}

	public void fileLock() throws IOException {
		RandomAccessFile raf = new RandomAccessFile("lockfile.txt", "rw");
		FileChannel fc = raf.getChannel();
		// doQuery...
		doUpdate(fc);
	}

	private void doQuery(FileChannel fc) throws IOException {
		FileLock lock = fc.lock(0, 10, true);
		try {
			// doQuery...
		} finally {
			lock.release();
		}
	}
	private void doUpdate(FileChannel fc) throws IOException {
		FileLock lock = fc.lock(0, 10, false);
		try {
			// doUpdate...
		} finally {
			lock.release();
		}
	}


	public void gather() throws IOException {
		ByteBuffer buffer1 = ByteBuffer.allocate(50);
		ByteBuffer buffer2 = ByteBuffer.allocate(50);

		buffer1.put("hello".getBytes());
		buffer2.put("world".getBytes());

		File tempFilePath = File.createTempFile("temp", null);
		RandomAccessFile tempFile = new RandomAccessFile(tempFilePath, "rw");
		FileChannel channel = tempFile.getChannel();

		ByteBuffer[] buffers = new ByteBuffer[2];
		buffers[1] = buffer1;
		buffer1.slice();
		buffers[2] = buffer2;

		channel.write(buffers);
	}

	public void asyConnect() throws IOException {
		InetSocketAddress addr = new InetSocketAddress("localhost", 8888);
		SocketChannel sc = SocketChannel.open();
		sc.configureBlocking(false);
		sc.connect(addr);

		while (!sc.finishConnect()) {
			// doSomting..
		}

		sc.close();
	}

	public void dataGram() throws IOException {
		DatagramChannel dc = DatagramChannel.open();
		ByteBuffer bb = ByteBuffer.allocate(100);

		dc.send(bb, new InetSocketAddress("localhost", 8888));

		bb.clear();
		dc.receive(bb);
	}

	public void pipeTest() throws IOException {
		WritableByteChannel out = Channels.newChannel(System.out);
		ReadableByteChannel workerChannel = startWorker(10);
		ByteBuffer buffer = ByteBuffer.allocate(100);
		while (workerChannel.read(buffer) >= 0) {
			buffer.flip();
			out.write(buffer);
			buffer.compact();
		}

	}

	private ReadableByteChannel startWorker(int reps) throws IOException {
		Pipe pipe = Pipe.open();
		Worker worker = new Worker(pipe.sink(), reps);
		worker.start();
		return pipe.source();
	}

	private static class Worker extends Thread {
		WritableByteChannel channel;
		private int reps;

		Worker (WritableByteChannel channel, int reps) {
			this.channel = channel;
			this.reps = reps;
		}

		public void run() {
			ByteBuffer buffer = ByteBuffer.allocate(100);
			try {
				for (int i = 0; i < reps; i++) {
					buffer.putLong(1000);
					while (buffer.hasRemaining()) {
						this.channel.write(buffer);
					}
				}
				this.channel.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
