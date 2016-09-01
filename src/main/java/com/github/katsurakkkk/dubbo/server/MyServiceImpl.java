package com.github.katsurakkkk.dubbo.server;

import com.github.katsurakkkk.dubbo.*;

/**
 * Created by katsurakkkk on 9/1/16.
 */
public class MyServiceImpl implements MyService{

	@Override
	public String sayHello() {
		return "Hello World.";
	}
}
