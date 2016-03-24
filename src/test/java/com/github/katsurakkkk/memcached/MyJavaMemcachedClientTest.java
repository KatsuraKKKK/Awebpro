package com.github.katsurakkkk.memcached;

import java.util.Date;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class MyJavaMemcachedClientTest {
	private MyJavaMemcachedClient myClient;
	
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
		myClient = MyJavaMemcachedClient.instance();
	}
	
	@After
	public void tearDown() {
		System.out.println("Tear down...");
	}
	
	@Test
	public void testSet() {
		String key = "testSet";
		String value = "Set";
		boolean result = myClient.set(key, value);
		Assert.assertTrue(result);
	}
	
	@Test
	public void testSetWithExptime() {
		String key = "testSetWithExptime";
		String value = "SetWithExptime";
		Date date = new Date(200);
		boolean result = myClient.set(key, value, date);
		Assert.assertTrue(result);
	}
	
	@Test
	public void testGet() {
		String key = "testGet";
		String value = "Get";
		myClient.set(key, "Get");
		String helloValue =(String)myClient.get(key);
		Assert.assertTrue(value.equals(helloValue));
	}
	
}
