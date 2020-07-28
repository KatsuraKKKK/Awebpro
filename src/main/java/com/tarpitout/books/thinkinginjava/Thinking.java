package com.tarpitout.books.thinkinginjava;

import java.util.Properties;

/**
 * Created by KatsuraKKKK on 2016/4/23 0023.
 */
public class Thinking {
    public static void main(String[] args) {
        Properties properties = System.getProperties();
        properties.list(System.out);
        Runtime rt = Runtime.getRuntime();
        System.out.println("Total Mem：" + rt.totalMemory() + "|" + "Free Mem: " + rt.freeMemory());
    }
}
