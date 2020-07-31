package com.tarpitout.aio.jdk.justtry;

public class Amain {
	public static void main(String[] args) {
		B b = new B();
		A a = b.getA();
		a.callAprint();
	}
}
