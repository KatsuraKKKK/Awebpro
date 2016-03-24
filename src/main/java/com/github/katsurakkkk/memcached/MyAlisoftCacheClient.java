package com.github.katsurakkkk.memcached;

import com.alisoft.xplatform.asf.cache.ICacheManager;
import com.alisoft.xplatform.asf.cache.IMemcachedCache;
import com.alisoft.xplatform.asf.cache.memcached.CacheUtil;
import com.alisoft.xplatform.asf.cache.memcached.MemcachedCacheManager;

public class MyAlisoftCacheClient {
	private ICacheManager<IMemcachedCache> manager;
	private IMemcachedCache cache;
	
	private MyAlisoftCacheClient() {

	}

	public static MyAlisoftCacheClient instance() {
		MyAlisoftCacheClient result = new MyAlisoftCacheClient();
		ICacheManager<IMemcachedCache> manager = CacheUtil.getCacheManager(IMemcachedCache.class,
				MemcachedCacheManager.class.getName());
		manager.setConfigFile("memcached.xml");
		manager.start();
		result.setManager(manager);
		result.setCache(manager.getCache("mclient_0"));
		return result;
	}
	
	public void stop() {
		if (manager != null) {
			manager.stop();
		}
	}
	
	public Object put(String key, String value) {
		return cache.put(key, value);
	}
	
	public Object get(String key) {
		return cache.get(key);
	}

	public ICacheManager<IMemcachedCache> getManager() {
		return manager;
	}

	public void setManager(ICacheManager<IMemcachedCache> manager) {
		this.manager = manager;
	}

	public IMemcachedCache getCache() {
		return cache;
	}

	public void setCache(IMemcachedCache cache) {
		this.cache = cache;
	}
}
