package com.yg;

// 大整数

import java.math.BigInteger;

public class Main {
    /**  */
    static void demo1 () {
        var a = new BigInteger("10000");
        var b = new BigInteger("10");

        a.add(b); // +
        a.subtract(b); // -
        a.divide(b); // *
        a.multiply(b); // /


        System.out.println(a.intValue());
        System.out.println(a.intValueExact()); // 溢出报错

        System.out.println(a.floatValue());

        System.out.println(a.mod(b).intValueExact()); // 取模
    }

    /** 注意注意注意了 */
    static void demo2 () {
        BigInteger n = new BigInteger("999999").pow(99);
        float f = n.floatValue();
        System.out.println(f); // Infinity
    }

    /* 看看是怎么报错的 */
    static void demo3 () {
        BigInteger n = new BigInteger("999999").pow(99);
        float f = n.intValueExact();
        System.out.println(f); // Infinity
    }

    public static void main(String[] args) {
        demo3();
    }
}
