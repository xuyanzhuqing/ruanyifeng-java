package com.itranswrap.world;

public class Main {
    public static void main(String[] args) {
        Person p = new Person();
        System.out.println(p.name);
        System.out.println(p.age); //  同一个包内可以访问
        System.out.println("world is a strange zoom");
    }
}
