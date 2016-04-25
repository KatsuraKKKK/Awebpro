package com.github.katsurakkkk.thinkinginjava.accesscontrol.a;

/**
 * Created by KatsuraKKKK on 2016/4/25 0025.
 */
public class A {
    public static A instance() {
        return new A();
    }
    protected void echo() {
        System.out.println("A");
    }
}
