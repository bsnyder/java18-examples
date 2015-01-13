package org.bsnyder.java18.examples;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

interface Executable {
    int execute(int a);
}

class Runner {
    public int run(Executable e) {
        System.out.println("Executing code block...");
        int x = e.execute(999);
        System.out.println(x);
        return x;
    }
}

public class Java8LambdasDemo
{
    public static void main( String[] args ) {
//        List<Integer> numbers = Arrays.asList(1,2,3,4,5,6,7,8,9);
//
//        numbers.forEach(new Consumer<Integer>() {
//            @Override
//            public void accept(Integer integer) {
//                System.out.println(integer);
//            }
//        });

        Runner r = new Runner();
        r.run(new Executable() {
            @Override
            public int execute(int a) {
                System.out.println("Simple Executable");
                return a+2;
            }
        });

        System.out.println("==========================");

        /*
        r.run(() -> {
            System.out.println("Testing a lambda");
            System.out.println();
            return 3;
        });
        */

        r.run(a -> {
            System.out.println("Adding a+3");
            return a+3;
        });
    }
}
