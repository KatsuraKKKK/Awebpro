package com.github.katsurakkkk;


/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        App app = new App();
        app.testEquals();
    }
    
    public void testEquals() {
    	A a = new A();
    	a.printClass();
    	B b = new B();
    	b.printClass();
    	C c = new C();
    	c.printClass1();
    }
    
    public class A {
    	public void printClass() {
    		System.out.println("A");
    	}
    }
    
    public class B extends A {
    	public void printClass() {
    		System.out.println("B");
    	}
    }
    
    public class C extends B {
    	public void printClass1() {
    		((A)this).printClass();
    	}
    }
}
