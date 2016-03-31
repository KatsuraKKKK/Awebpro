package com.github.katsurakkkk.collections;

import java.util.SortedMap;
import java.util.TreeMap;

public class TreeMapTry {
	public static void main(String[] args) {
		TreeMapTry tmt = new TreeMapTry();
		tmt.testTreeMap();
	}
	
	public void testTreeMap() {
		TreeMap<Integer, String> map = new TreeMap<Integer, String>();
		
		map.put(3, "Three");
		map.put(8, "Five");
		map.put(6, "Four");
		
		SortedMap<Integer, String> smap = map.tailMap(9);
		
		System.out.println(smap.firstKey());
		
	}
}
