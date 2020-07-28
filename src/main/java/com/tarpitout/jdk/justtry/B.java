package com.tarpitout.jdk.justtry;

public class B extends A{
	public void aprint(){
		System.out.println("B");
	}
	
	public A getA() {
		return new A() {
			public void aprint() {
				B.this.aprint();
				System.out.println("A");
			}
		};
	}
}
