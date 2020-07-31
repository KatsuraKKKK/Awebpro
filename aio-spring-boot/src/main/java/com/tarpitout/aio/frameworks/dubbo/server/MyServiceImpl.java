package com.tarpitout.aio.frameworks.dubbo.server;

import com.tarpitout.aio.frameworks.dubbo.MyService;

/**
 * Created by katsurakkkk on 9/1/16.
 */
public class MyServiceImpl implements MyService {

	@Override
	public String sayHello() {
		return "Hello World.";
	}
}
