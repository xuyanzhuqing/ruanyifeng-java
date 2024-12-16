package com.yg;

// 大浮点数

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Main {
    /**  */
    static void demo1 () {
        BigDecimal d1 = new BigDecimal("100.101");
        BigDecimal d2 = new BigDecimal("100.1010");

        // 数据精度
        System.out.println(d1.scale()); // 3
        System.out.println(d2.scale()); // 4
    }

    /**  */
    static void demo2 () {
        BigDecimal d1 = new BigDecimal("100.101");
        BigDecimal d2 = new BigDecimal("100.1010");

        System.out.println(d1.equals(d2)); // 不同 scale 数据不相等

        var d3 = d2.stripTrailingZeros();
        System.out.println(d3.scale()); // 3
        System.out.println(d1.equals(d3));

        // 注意注意注意，scale 为负数时，表示整数后面有几个零,
        var d4 = new BigDecimal("10000");
        System.out.println(d4.scale()); // 0， wtf 什么时候不对了
    }

    /** 设置精度 */
    static void demo3 () {
        BigDecimal d1 = new BigDecimal("100.1064");
        System.out.println(d1.setScale(3, RoundingMode.HALF_UP)); // 直接截断
        System.out.println(d1.setScale(2, RoundingMode.DOWN)); // 四舍五入
    }

    /** 比较大小  */
    static void demo4 () {
        BigDecimal d1 = new BigDecimal("100.101");
        BigDecimal d2 = new BigDecimal("100.1010");

        System.out.println(d1.equals(d2));
        System.out.println(d1.compareTo(d2));
        /**
         * d1 < d2  -> -1
         * d1 > d2  ->  1
         * d1 == d2 ->  0
         * */
    }

    public static void main(String[] args) {
        demo3();
    }
}
