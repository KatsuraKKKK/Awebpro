package com.tarpitout.frameworks.dubbo.server;

import org.springframework.context.support.*;

/**
 * Created by katsurakkkk on 9/1/16.
 */
public class Server {
	public static void main(String[] args) throws Exception {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("dubbo/server.xml");
		context.start();

		System.in.read(); // 为保证服务一直开着，利用输入流的阻塞来模拟
	}
}
