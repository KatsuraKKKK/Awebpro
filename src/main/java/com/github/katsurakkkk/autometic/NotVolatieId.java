package com.github.katsurakkkk.autometic;

import java.util.Random;

public class NotVolatieId {
	public long id;
	
	public NotVolatieId(long id) {
		this.id = id;
		Random random = new Random();
		long result = 0;
		for (int i = 0; i<10000; i++) {
			result += random.nextInt(1000);
		}
		result = result -1;
	}
	
	public long get () {
		return id;
	}
}