package com.tarpitout.jdk.nio;

import java.io.*;
import java.net.*;
import java.nio.*;
import java.nio.channels.*;
import java.util.*;

/**
 * Created by katsurakkkk on 9/5/16.
 */
public class SelectSockets {
	public static int PORT_NUMBER = 1234;

	private ByteBuffer buffer = ByteBuffer.allocateDirect(1024);

	public static void main(String[] argv) throws Exception {
		new SelectSockets().go(argv);
	}

	public void go(String[] argv) throws Exception {
		int port = PORT_NUMBER;

		if (argv.length > 0) {
			port = Integer.parseInt(argv[0]);
		}

		System.out.println("Listening on port " + port);

		ServerSocketChannel serverChannel = ServerSocketChannel.open();
		ServerSocket serverSocket = serverChannel.socket();
		Selector selector = Selector.open();

		serverSocket.bind(new InetSocketAddress(port));
		serverChannel.configureBlocking(false);

		serverChannel.register(selector, SelectionKey.OP_ACCEPT);
		while (true) {
			int n = selector.select();

			if (n == 0) {
				continue;
			}

			Iterator it = selector.selectedKeys().iterator();
			while (it.hasNext()) {
				SelectionKey key = (SelectionKey) it.next();

				if (key.isAcceptable()) {
					ServerSocketChannel server = (ServerSocketChannel) key.channel();
					SocketChannel channel = server.accept();

					registerChannel(selector, channel, SelectionKey.OP_READ);

					sayHello(channel);
				}

				if (key.isReadable()) {
					readDataFromSocket(key);
				}

				it.remove();
			}
		}
	}

	protected void readDataFromSocket(SelectionKey key) throws IOException {
		SocketChannel socketChannel = (SocketChannel) key.channel();
		int count;

		buffer.clear();

		while ((count = socketChannel.read(buffer)) > 0) {
			buffer.flip();

			while (buffer.hasRemaining()) {
				socketChannel.write(buffer);
			}

			buffer.clear();
		}

		if (count < 0) {
			socketChannel.close();
		}
	}

	protected void sayHello(SocketChannel channel) throws IOException {
		buffer.clear();
		buffer.put("Hello world".getBytes());
		buffer.flip();

		channel.write(buffer);
	}

	protected void registerChannel(Selector selector, SocketChannel channel, int ops) throws IOException {
		if (channel == null) {
			return;
		}
		channel.configureBlocking(false);
		channel.register(selector, ops);
	}
}
