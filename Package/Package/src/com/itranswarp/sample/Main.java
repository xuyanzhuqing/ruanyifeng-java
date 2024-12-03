package com.itranswarp.sample;

import com.itranswrap.world.Person;

public class Main {
     public static void main(String[] args) {
         Person p = new Person();
         System.out.println(p.name);
         // System.out.println(p.age); // 没有声明 public 跨包无法访问
         System.out.println("main class is running");
    }
}
