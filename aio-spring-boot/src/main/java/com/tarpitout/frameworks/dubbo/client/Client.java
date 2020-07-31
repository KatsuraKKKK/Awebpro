package com.tarpitout.frameworks.dubbo.client;

import com.tarpitout.frameworks.dubbo.*;
import org.springframework.context.support.*;

/**
 * Created by katsurakkkk on 9/1/16.
 */
public class Client {
	public static void main(String[] args) throws Exception {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("dubbo/client.xml");
		context.start();

		MyService myService = (MyService) context.getBean("myService");
		String hello = myService.sayHello();
		System.out.println(hello);
	}
}
