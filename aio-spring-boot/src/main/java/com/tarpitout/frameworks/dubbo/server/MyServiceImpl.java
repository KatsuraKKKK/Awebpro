package com.tarpitout.frameworks.dubbo.server;

import com.tarpitout.frameworks.dubbo.*;

/**
 * Created by katsurakkkk on 9/1/16.
 */
public class MyServiceImpl implements MyService{

	@Override
	public String sayHello() {
		return "Hello World.";
	}
}
