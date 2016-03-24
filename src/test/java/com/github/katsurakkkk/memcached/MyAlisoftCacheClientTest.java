package com.github.katsurakkkk.memcached;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class MyAlisoftCacheClientTest {
	private MyAlisoftCacheClient myClient;
	
	@BeforeClass
	public static void before() {
		System.out.println("Before class...");
	}
	
	@AfterClass
	public static void after() {
		System.out.println("After class...");
	}
	
	@Before
	public void setUp() {
		myClient = MyAlisoftCacheClient.instance();
	}
	
	@After
	public void tearDown() {
		System.out.println("Tear down...");
	}
	
	@Test
	public void testGet() {
		String key = "testGet";
		String value = "Get";
		myClient.put(key, "Get");
		String helloValue =(String)myClient.get(key);
		Assert.assertTrue(value.equals(helloValue));
	}
	
}
