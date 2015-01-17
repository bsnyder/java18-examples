package org.bsnyder.java18.examples;

import java.util.function.Supplier;

interface Shape {
    void sketch();
}

class Rectangle implements Shape {

    @Override
    public void sketch() {
        System.out.println("Rectangle.sketch() method invoked");
    }

    public String toString() {
        return "Rectangle";
    }
}

class Triangle implements Shape {

    @Override
    public void sketch() {
        System.out.println("Triangle.sketch() method invoked");
    }

    public String toString() {
        return "Triangle";
    }
}

class Circle implements Shape {

    @Override
    public void sketch() {
        System.out.println("Circle.sketch() method invoked");
    }

    public String toString() {
        return "Circle";
    }
}

interface Animal {
    void foo();
}

class Dog implements Animal {

    @Override
    public void foo() {
        System.out.println("Dog.foo() method invoked");
    }

    public String toString() {
        return "Dog";
    }
}

class Ferret implements Animal {

    @Override
    public void foo() {
        System.out.println("Ferret.foo() method invokved");
    }

    public String toString() {
        return "Ferret";
    }
}

class Factory<T> {

    public T get(Supplier<? extends T> supplier) {
        T t = supplier.get();
        System.out.println(t + " was returned");
        return t;
    }
}

public class SupplierDemo {

    public static void main(String[] args) {
        Factory<Shape> shapeFactory = new Factory<>();
        Shape circle = shapeFactory.get(() -> new Circle());
        circle.sketch();
        System.out.println("-----------------------------");
        Shape rectangle = shapeFactory.get(() -> new Rectangle());
        rectangle.sketch();
        System.out.println("-----------------------------");
        Shape triangle = shapeFactory.get(() -> new Triangle());
        triangle.sketch();

        System.out.println("-----------------------------");
        Factory<Animal> animalFactory = new Factory<>();
        Animal dog = animalFactory.get(() -> new Dog());
        dog.foo();
        System.out.println("-----------------------------");
        Animal ferret = animalFactory.get(() -> new Ferret());
        ferret.foo();
    }
}
