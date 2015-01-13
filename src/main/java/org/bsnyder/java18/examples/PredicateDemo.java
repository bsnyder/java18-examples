package org.bsnyder.java18.examples;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

class MyPredicate<T> {

    public List<T> testPredicate(Predicate<T> p, List<T> numbers) {
        List<T> answer = new ArrayList<>();

        numbers.forEach( x -> {
            if (p.test(x)) {
                answer.add(x);
            }
        });

        return answer;
    }

    public List<T> testPredicateNegate(Predicate<T> p, List<T> l) {
        List<T> answer = new ArrayList<>();

        /*

        Q: Why does external iteration not result in proper negation?

        l.forEach( x -> {
            if (p.negate().test(x));
            answer.add(x);
        });
        */

        for (T t : l) {
            if (p.negate().test(t)) answer.add(t);
        }

        return answer;
    }

    public List<T> testPredicateOr(Predicate<T> p1, Predicate<T> p2, List<T> l) {
        List<T> answer = new ArrayList<>();

        for( T t : l) {
            if (p1.or(p2).test(t)) answer.add(t);
        }

        return answer;
    }

    public List<T> testPredicateAnd(Predicate<T> p1, Predicate<T> p2, List<T> l) {
        List<T> answer = new ArrayList<>();

        for( T t : l) {
            if (p1.and(p2).test(t)) answer.add(t);
        }

        return answer;
    }

}

/**
 * Created by bsnyder on 1/13/15.
 */
public class PredicateDemo {

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(0,1,2,3,4,5,6,7,8,9,10,11,12,13);

        MyPredicate<Integer> intPredicate = new MyPredicate<Integer>();
        System.out.println(" All numbers: " + intPredicate.testPredicate(x -> true, numbers));
        System.out.println("Even numbers: " + intPredicate.testPredicate(x -> (x & 1) == 0, numbers));
        System.out.println(" Odd numbers: " + intPredicate.testPredicate(x -> (x & 1) == 1, numbers));
        System.out.println();
        System.out.println(" Negate all numbers: " + intPredicate.testPredicateNegate(x -> true, numbers));
        System.out.println("Negate even numbers: " + intPredicate.testPredicateNegate(x -> (x & 1) == 0, numbers));
        System.out.println(" Negate odd numbers: " + intPredicate.testPredicateNegate(x -> (x & 1) == 1, numbers));

        System.out.println("###################################################");

        MyPredicate<String> stringPredicate = new MyPredicate<>();
        List<String> strings = Arrays.asList("one", "two", "three", "four", "five", "six", "seven", "eight");
        System.out.println("All strings: " + stringPredicate.testPredicate(x -> true, strings));
        System.out.println("Contains ee: " + stringPredicate.testPredicate(x -> x.contains("ee"), strings));
        System.out.println(" Contains t: " + stringPredicate.testPredicate(x -> x.contains("t"), strings));
        System.out.println();
        System.out.println("Negate all strings: " + stringPredicate.testPredicateNegate(x -> true, strings));
        System.out.println("Negate contains ee: " + stringPredicate.testPredicateNegate(x -> x.contains("ee"), strings));
        System.out.println(" Negate contains t: " + stringPredicate.testPredicateNegate(x -> x.contains("t"), strings));

        System.out.println("###################################################");

        MyPredicate<Object> objPredicate = new MyPredicate<>();
        List<Object> objects = Arrays.asList(new String("one"), new String("two"), new StringBuilder("three"));
        System.out.println("       All objects: " + objPredicate.testPredicate(x -> true, objects));
        System.out.println("       Type String: " + objPredicate.testPredicate(x -> x instanceof String, objects));
        System.out.println("Type StringBuilder: " + objPredicate.testPredicate(x -> x instanceof StringBuilder, objects));
        System.out.println();
        System.out.println("       Negate all objects: " + objPredicate.testPredicateNegate(x -> true, objects));
        System.out.println("       Negate type String: " + objPredicate.testPredicateNegate(x -> x instanceof String, objects));
        System.out.println("Negate type StringBuilder: " + objPredicate.testPredicateNegate(x -> x instanceof StringBuilder, objects));

        System.out.println("###################################################");

        System.out.println("Even or odd numbers: " +
                intPredicate.testPredicateOr(x -> (x & 1) == 0, x -> (x & 1) == 1, numbers));
        System.out.println("Even or odd numbers: " +
                intPredicate.testPredicateAnd(x -> (x & 1) == 0, x -> (x & 1) == 1, numbers));
    }
}


