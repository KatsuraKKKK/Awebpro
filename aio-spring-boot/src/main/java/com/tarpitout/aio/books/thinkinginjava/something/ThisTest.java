package com.tarpitout.aio.books.thinkinginjava.something;

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
		if (i > 1) {
			System.out.println("1-2");
		}
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


abstract class Contents {
	abstract public int value();
}

interface Destination {
	String readLabel();
}

class Parcel {
	private class PContents extends Contents {
		private int i = 11;

		@Override
		public int value() {
			return i;
		}
	}

	protected class PDestination implements Destination {
		private String label;

		private PDestination(String whereTo) {
			label = whereTo;
		}

		@Override
		public String readLabel() {
			return label;
		}
	}

	public Destination dest(String s) {
		return new PDestination(s);
	}

	public Contents cont() {
		return new PContents();
	}
}

class Test {
	public static void main(String[] args) {
		Parcel p = new Parcel();
		Contents c = p.cont();
		Destination d = p.dest("ShenZhen");
		// Illegal -- can't access private class:
		//! Parcel.PContents c = p.new Pcontents();
	}
}

class WithInner {
	class Inner {}
}

class InheritInner extends WithInner.Inner {
	// InheritInner() {} // Compile error.
	InheritInner(WithInner wi) {
		wi.super();
	}
}