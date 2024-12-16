package com.yg;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    /** 最简单的demo */
    static void demo1 () {
        try {
            String s = "abc";
            int n = Integer.parseInt(s);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }
    
    /** 吸烟有害健康就是一个笑话 */
    static void demo2 () {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("你成年了吗，今年多少岁");
            String s = scanner.nextLine();
            int age = Integer.parseInt(s);
            Main.smoke(age);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            System.out.println("问你话呢，今年多少岁了");
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            System.out.println("吸烟有什么好的，小小年纪不学好");
        }
    }

    /** 断言的使用 仅开发调试可用 需加入 -enableassertion or -ea 方可开启*/
    static void demo3 () {
        System.out.println("你的年龄多少岁了");
        Scanner scanner = new Scanner(System.in);
        int age = scanner.nextInt();

        assert  age < 18: "未成年人禁止入内";
    }

    static void smoke (int age) {
        if (age < 18) {
            throw new IllegalArgumentException("未成年人禁止吸烟");
        }
        System.out.println("吸烟有害健康");
        System.out.println("终于没人管了");
    }
    
    public static void main(String[] args) {
        demo3();
    }
}
