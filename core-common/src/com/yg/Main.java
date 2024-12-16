package com.yg;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.HexFormat;
import java.util.Random;

public class Main {
    /** 常见类-Math */
    static void demo1 () {
        Math.abs(-1); // 绝对值
        Math.min(-1, -2);
        Math.max(1, 2);
        Math.sqrt(4); // 开根号
        Math.pow(2, 3); // 2的3次方

        Math.exp(2); // e 的平方

        Math.log(1); // 以 e 为底的对数
        Math.log10(1); // 以 10 为底的对数

        Math.sin(3.14); // 0.00159...
        Math.cos(3.14); // -0.9999...
        Math.tan(3.14); // -0.0015...
        Math.asin(1.0); // 1.57079...
        Math.acos(1.0); // 0.0

        double pi = Math.PI; // 3.14159...
        double e = Math.E; // 2.7182818...
        Math.sin(Math.PI / 6); // sin(π/6) = 0.5

        Math.random(); //随机数
    }
    
    /** 十六进制处理 */
    static void demo2 () {
        byte[] data = "Hello".getBytes();

        // byte 转十六进制
        HexFormat hf = HexFormat.of();
        String hexData = hf.formatHex(data); // 48656c6c6f

        // 十六进制转byte
        byte[] bs = HexFormat.of().parseHex(hexData);

        for (byte b: bs) {
            System.out.println((char)b);
        }

        // 自定义转义
        HexFormat myHf = HexFormat.ofDelimiter(" ").withPrefix("0x").withUpperCase();
        System.out.println(myHf.formatHex("Hello".getBytes())); // 0x48 0x65 0x6C 0x6C 0x6F

    }

    /** 伪随机 */
    static void demo3 () {
        // 以系统当前时间戳为种子的随机数
        Random r = new Random();
        for (int i = 0; i < 10; i++) {
            System.out.println(r.nextInt());
        }

        // 固定种子的随机数
        int seed = 12345;
        Random r1 = new Random(seed);
        for (int i = 0; i < 5; i++) {
            System.out.println(r1.nextInt());
        }
    }

    /* 安全的随机数 */
    static void demo4 () {
        Random r1 = new SecureRandom();
        for (int i = 0; i < 5; i++) {
            System.out.println(r1.nextInt());
        }
    }

    /** 随机数的终极解决方案 */
    static void demo5 () {
        SecureRandom sr = null;
        try {
            sr = SecureRandom.getInstanceStrong(); // 获取高强度安全随机数生成器
        } catch (NoSuchAlgorithmException e) {
            sr = new SecureRandom(); // 获取普通的安全随机数生成器
        }
        byte[] buffer = new byte[16];
        sr.nextBytes(buffer); // 用安全随机数填充buffer
        System.out.println(Arrays.toString(buffer));
    }

    public static void main(String[] args) {
        demo3();
    }
}
