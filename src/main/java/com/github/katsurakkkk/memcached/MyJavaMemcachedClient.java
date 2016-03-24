package com.github.katsurakkkk.memcached;

import java.util.Date;

import com.danga.MemCached.MemCachedClient;
import com.danga.MemCached.SockIOPool;

public class MyJavaMemcachedClient {
	private MemCachedClient client;
	private SockIOPool sockIoPool;
	private static String[] addr = { "127.0.0.1:11211" };
	private static Integer[] weights = { 3 };

	private MyJavaMemcachedClient() {

	}

	public static MyJavaMemcachedClient instance() {
		MyJavaMemcachedClient result = new MyJavaMemcachedClient();
		SockIOPool pool = SockIOPool.getInstance();
		pool.setServers(addr);
		pool.setWeights(weights);
		pool.setInitConn(5);
		pool.setMinConn(5);
		pool.setMaxConn(200);
		pool.setMaxIdle(1000 * 30 * 30);
		pool.setMaintSleep(30);
		pool.setNagle(false);
		pool.setSocketTO(30);
		pool.setSocketConnectTO(0);
		pool.initialize();
		result.setSockIoPool(pool);

		MemCachedClient client = new MemCachedClient();
		// String [] s =pool.getServers();
		// client.setCompressEnable(true);
		// client.setCompressThreshold(1000*1024);
		result.setClient(client);
		return result;
	}

	public boolean set(String key, String value) {
		return client.set(key, value);
	}

	public boolean set(String key, String value, Date exptime) {
		return client.set(key, value, exptime);
	}

	public Object get(String key) {
		return client.get(key);
	}

	public boolean delete(String key) {
		return client.delete(key);
	}

	public MemCachedClient getClient() {
		return client;
	}

	public void setClient(MemCachedClient client) {
		this.client = client;
	}

	public SockIOPool getSockIoPool() {
		return sockIoPool;
	}

	public void setSockIoPool(SockIOPool sockIoPool) {
		this.sockIoPool = sockIoPool;
	}
}
