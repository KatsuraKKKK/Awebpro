package com.tarpitout.aio.jdk.nio;

import java.io.*;
import java.nio.*;
import java.nio.channels.*;
import java.util.concurrent.*;

/**
 * Created by katsurakkkk on 9/5/16.
 */
public class SelectSocketsThreadPool extends SelectSockets {
	private static final int MAX_THREADS = 5;

	private ExecutorService executorService = Executors.newFixedThreadPool(MAX_THREADS);

	public static void main(String[] argv) throws Exception {
		new SelectSocketsThreadPool().go(argv);
	}

	@Override
	protected void readDataFromSocket(SelectionKey key) throws IOException {
		key.interestOps(key.interestOps() & (~SelectionKey.OP_READ));
		executorService.submit(new WorkerThread(key));
	}

	@Override
	protected void sayHello(SocketChannel channel) throws IOException {
		super.sayHello(channel);
	}

	@Override
	protected void registerChannel(Selector selector, SocketChannel channel, int ops) throws IOException {
		super.registerChannel(selector, channel, ops);
	}

	private class WorkerThread extends Thread {
		private SelectionKey key;
		ByteBuffer buffer = ByteBuffer.allocate(1024);

		WorkerThread(SelectionKey key) {
			this.key = key;
		}

		public void run() {
			if (key == null) {
				return;
			}

			try {
				drainChannel(key);
			} catch (Exception e) {
				System.out.println("Close channel");
				try {
					key.channel().close();
				} catch (IOException ex) {
					e.printStackTrace();
				}
			}
		}

		void drainChannel(SelectionKey key) throws Exception {
			SocketChannel channel = (SocketChannel) key.channel();

			int count;

			buffer.clear();

			while ((count = channel.read(buffer)) > 0) {
				buffer.flip();
				while (buffer.hasRemaining()) {
					channel.write(buffer);
				}
				buffer.clear();
			}

			if (count < 0) {
				channel.close();
				return;
			}

			key.interestOps(key.interestOps() | SelectionKey.OP_READ);
			key.selector().wakeup();
		}
	}
}


