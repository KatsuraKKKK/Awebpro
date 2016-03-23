package com.github.katsurakkkk;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws Exception
    {
    	try {
    		System.out.println("try");
    		aException();
    	} catch (Exception e) {
    		System.out.println("catch before");
    		throw new Exception();
    	}finally {
    		System.out.println("finally");
    	}
    	long WORD_MASK = 0xffffffffffffffffL;
    	System.out.println(Long.toBinaryString(WORD_MASK));
    	long WORD_MASK1 = 0xffffffffffffffffL << 1;
    	System.out.println(Long.toBinaryString(WORD_MASK1));
    	long WORD_MASK2 = 0xffffffffffffffffL << 64;
    	System.out.println(Long.toBinaryString(WORD_MASK2));
    	long WORD_MASK3 = 0xffffffffffffffffL << 66;
    	System.out.println(Long.toBinaryString(WORD_MASK3));
    	long WORD_MASK4 = 0xffffffffffffffffL >>> 4;
//    	System.out.println(StringUtils.leftPad(Long.toBinaryString(WORD_MASK4), 64, "0"));
//    	System.out.println(String.format("%010x", WORD_MASK4));
        App app = new App();
        app.testEquals();
        
        List<String> alist = new ArrayList<String>();
        Collections.unmodifiableCollection(alist);
    }
    
    private static void aException() throws Exception {
    	throw new Exception();
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
