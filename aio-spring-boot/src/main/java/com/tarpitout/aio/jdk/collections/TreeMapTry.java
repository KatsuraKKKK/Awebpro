package com.tarpitout.aio.jdk.collections;


import com.alibaba.fastjson.JSONObject;

import java.nio.CharBuffer;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class TreeMapTry {
	public static void main(String[] args) {
		char[] a = {'a','b'};
		System.out.println(a.length);
		CharBuffer charBuffer = CharBuffer.allocate(a.length);
		System.out.println();
//		TreeMapTry tmt = new TreeMapTry();
//		tmt.testTreeMap();
		Map<String, String> map = (Map) JSONObject.parseObject("{'a':'1', 'b':'c'}");
        Object b = map.get("b");
        System.out.println(b);
	}
	
	public void testTreeMap() {
		TreeMap<Integer, String> map = new TreeMap<Integer, String>();
		
		map.put(3, "Three");
		map.put(8, "Five");
		map.put(6, "Four");
		
		SortedMap<Integer, String> smap = map.tailMap(6);
		
		System.out.println(smap.firstKey());
		
	}
}
