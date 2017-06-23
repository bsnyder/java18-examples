package org.bsnyder.java18.examples;

import java.util.HashMap;
import java.util.Map;

import java.util.function.Consumer;

public class ForEachExample {

    public static void main(String[] args) {
        Map<String, Integer> stuff = new HashMap<String, Integer>();
        stuff.put("A", 1);
        stuff.put("B", 2);
        stuff.put("C", 3);
        stuff.put("D", 4);
        stuff.put("E", 5);
        stuff.put("F", 6);
        stuff.put("G", 7);
        stuff.put("H", 8);
        stuff.put("I", 9);
        stuff.put("J", 10);

        stuff.forEach((k, v) -> {
            System.out.println("key: " + k + " value: " + v);
        });
    }
}
