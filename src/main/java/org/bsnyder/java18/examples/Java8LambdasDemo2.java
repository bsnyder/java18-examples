package org.bsnyder.java18.examples;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Person {
    String name;
    String country;
    int age;

    public Person(String name, String country, int age) {
        this.name = name;
        this.country = country;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public int getAge() {
        return age;
    }

    public String toString() {
        return "{Person => name: " + name + " country: " + country + " age: " + age + "}";
    }
}

public class Java8LambdasDemo2 {

    public static void main(String[] args) {
        List<Person> people = Arrays.asList(
                new Person("Bailey", "USA", 12),
                new Person("Jade", "USA", 16),
                new Person("Janene", "Mexico", 43),
                new Person("Bruce", "Canada", 43),
                new Person("Diane", "Germany", 64),
                new Person("Jim", "Germany", 64),
                new Person("Susan", "USA", 62),
                new Person("John", "Canada", 64));

        System.out.println("==== People from the US :: filter ====");
        people.stream()
                .filter(person -> person.getCountry().equals("USA"))
                .map((Person person) -> "name: " + person.getName() + " country: " + person.getCountry())
                .forEach(person -> System.out.println(person));

        System.out.println("\n==== People not from the US :: filter ====");
        people.stream()
                .filter(person -> !person.getCountry().equals("USA"))
                .map((Person person) -> "name: " + person.getName() + " country: " + person.getCountry())
                .forEach(person -> System.out.println(person));

        System.out.println("\n==== People from the US :: collect w/ partitioningBy ====");
        System.out.println(people.stream()
                .collect(Collectors.partitioningBy((Person p) -> p.getCountry().equals("USA"))));

        System.out.println("\n==== People from the US :: collect w/ groupingBy ====");
        System.out.println(people.stream()
                .collect(Collectors.groupingBy((Person p) -> p.getCountry().equals("USA"))));

        System.out.println("\n==== People from the US :: collect w/ groupingBy and counting ====");
        System.out.println(people.stream()
                .collect(Collectors.groupingBy((Person p) -> p.getCountry().equals("USA"), Collectors.counting())));

        System.out.println("\n==== People from the US :: collect w/ groupingBy and counting ====");
        System.out.println(people.stream()
                .collect(Collectors.groupingBy((Person p) -> p.getCountry().equals("USA"), Collectors.counting())));

        System.out.println("\n==== People in each country :: collect w/ partitioningBy and counting ====");
        System.out.println(people.stream()
                .collect(Collectors.groupingBy((Person p) -> p.getCountry())));
        System.out.println(people.stream()
                .collect(Collectors.groupingBy((Person p) -> p.getCountry(), Collectors.counting())));

        System.out.println("\n==== People in USA and non-USA :: collect w/ partitioningBy and mapping ====");
        System.out.println(people.stream()
                .collect(Collectors.partitioningBy((Person p) -> p.getCountry().equals("USA"),
                                                    Collectors.mapping(p -> p.getName().toUpperCase(),
                                                    Collectors.toList()))));

        System.out.println("\n==== People in USA and non-USA :: collect w/ groupingBy and mapping ====");
        System.out.println(people.stream()
                .collect(Collectors.groupingBy((Person p) -> p.getCountry(),
                        Collectors.mapping(p -> p.getName().toUpperCase(),
                                Collectors.toList()))));

    }
}
