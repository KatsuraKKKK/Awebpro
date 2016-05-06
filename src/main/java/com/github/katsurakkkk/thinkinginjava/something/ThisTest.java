package com.github.katsurakkkk.thinkinginjava.something;

/**
 * Created by KatsuraKKKK on 2016/5/6 0006.
 */
public class ThisTest {
	public ThisTest() {
//		System.out.print("Constructor");
		this(1);
//		this(1, 2);
	}

	public static void main(String[] args) {
		InitCodeTest ict = new InitCodeTest();
		ict.printProperties();
	}

	public ThisTest(int n) {

	}

	public ThisTest(int n, int m) {

	}
}

class PropertyInitTest {
	int i = init();
	int j = init(i);

//  Compile Error Here.
//	int n = init(m);
//	int m = init();

	public int init() {
		return 1;
	}

	public int init(int n) {
		return n + 1;
	}
}

class InitCodeTest {
	int i;
	int j;

	public InitCodeTest() {
		System.out.println("Constructor.");
	}

	{
		i = increase(1);
		j = increase(2);
	}

	int increase(int n) {
		System.out.println("increase:" + n);
		return n + 1;
	}

	void printProperties() {
		System.out.println("i=" + i + "|j=" + j);
	}
}
