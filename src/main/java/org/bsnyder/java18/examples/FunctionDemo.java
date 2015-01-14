package org.bsnyder.java18.examples;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

class MyFunction<T,R> {

    public List<R> applyFunction(Function<T,R> function, List<T> numbers) {
        List<R> answer = new ArrayList<R>();

        numbers.forEach(x -> answer.add(function.apply(x)));

        return answer;
    }

    public List<R> applyFunctionAndThen(Function<T,R> function1, Function<R,R> function2, List<T> numbers) {
        List<R> answer = new ArrayList<R>();
        numbers.forEach(x -> answer.add(function1.andThen(function2).apply(x)));
        return answer;
    }

    public List<R> applyFunctionCompose(Function<R,R> function1, Function<T,R> function2, List<T> numbers) {
        List<R> answer = new ArrayList<R>();
        numbers.forEach(x -> answer.add(function1.compose(function2).apply(x)));
        return answer;
    }
}

public class FunctionDemo {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(0,1,2,3,4,5,6,7,8,9);

        MyFunction<Integer, Double> integer2DoubleFunction = new MyFunction<Integer, Double>();
        System.out.println("          Create new Double: " + integer2DoubleFunction.applyFunction(x -> new Double(x), numbers));
        System.out.println("     Create new Double * .5: " + integer2DoubleFunction.applyFunction(x -> new Double(x * .5), numbers));
        System.out.println("Two functions using andThen: " +
                integer2DoubleFunction.applyFunctionAndThen(x -> new Double(x) + new Double(x), x -> new Double(x) * new Double(x), numbers));
        System.out.println("Two functions using compose: " +
                integer2DoubleFunction.applyFunctionCompose(x -> new Double(x) + new Double(x), x -> new Double(x) * new Double(x), numbers));

        System.out.println("----------------------------------");

        MyFunction<Integer, Float> integer2FloatFunction = new MyFunction<Integer, Float>();
        System.out.println("           Create new Float: " + integer2FloatFunction.applyFunction(x -> new Float(x), numbers));
        System.out.println("      Create new Float * .5: " + integer2FloatFunction.applyFunction(x -> new Float(x * .5), numbers));
        System.out.println("Two functions using andThen: " +
                integer2FloatFunction.applyFunctionAndThen(x -> new Float(x) + new Float(x), x -> new Float(x) * new Float(x), numbers));
        System.out.println("Two functions using compose: " +
                integer2FloatFunction.applyFunctionCompose(x -> new Float(x) + new Float(x), x -> new Float(x) * new Float(x), numbers));
    }
}

