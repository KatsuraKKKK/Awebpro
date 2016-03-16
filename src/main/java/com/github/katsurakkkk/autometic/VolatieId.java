package com.github.katsurakkkk.autometic;

public class VolatieId {
	private volatile long id;
	
	public VolatieId(long id) {
		this.id = id;
	}
	
	public long get () {
		return id;
	}
}
