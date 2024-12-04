package com.yg;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.StringJoiner;

public class Main {
    static void demo1 () {
        String s1 = "hello";
        String s2 = new String(new char[] { 'h', 'e', 'l', 'l', 'o' });

        System.out.println(s1.equals(s2));
    }

    static void demo2 () {
        String s = "Hello";
        System.out.println(s); // Hello
        s = s.toUpperCase();
        System.out.println(s); //HELLO
    }

    /** 字符串相等 */
    static void demo3 () {
        String s1 = "hello";
        String s2 = "hello".toLowerCase();
        String s3 = "hello";
        System.out.println(s1 == s2); // false
        System.out.println(s1 == s3); // true Java编译器在编译期，会自动把所有相同的字符串当作一个对象放入常量池

        // 判断相等
        System.out.println(s1.equals(s3));
        System.out.println(s1.equalsIgnoreCase(s2));
    }

    /** 搜索字符串 */
    static void demo4 () {
        System.out.println("Hello".indexOf("l"));
        System.out.println("Hello".lastIndexOf("l"));
        System.out.println("Hello".contains("He"));
        System.out.println("Hello".startsWith("H"));
        System.out.println("Hello".endsWith("o"));
    }

    static void print (String str) {
        System.out.print("--");
        System.out.print(str);
        System.out.print("--\n");
    }

    /** 去除首尾空格 */
    static void demo5 () {
        Main:print(" \t Hello \r\n".trim());

        // \u3000 中文空格会被移除
        Main.print("\u3000Hello\u3000".strip());
        Main.print(" Hello ".stripLeading()); // 前方空格
        Main.print(" Hello ".stripTrailing()); // 后方空格
    }

    /** 替换字符串  */
    static void demo6 () {
        String s = "hello";
        s.replace('l', 'w'); // "hewwo"，所有字符'l'被替换为'w'
        s.replace("ll", "~~"); // "he~~o"，所有子串"ll"被替换为"~~"

        String s1 = "A,,B;C ,D";
        s1.replaceAll("[\\,\\;\\s]+", ","); // "A,B,C,D"
    }

    /** 分割 & 拼接字符串 */
    static void demo7 () {
        String str = "A,B,C,D";
        String[] arr = str.split("\\,");

        for (String s: arr) {
            System.out.println(s);
        }

        System.out.println(String.join("**", arr));
    }

    /** 格式化字符串 */
    static void demo8 () {
        String s = "Hi %s, your score is %d!";
        System.out.println(s.formatted("Alice", 80));
        System.out.println(String.format("Hi %s, your score is %.2f!", "Bob", 59.5));
    }

    /** 类型转换为 String */
    static void demo9 () {
        // 将其他类型转化为 string
        String[] strings = {
            String.valueOf(123),
            String.valueOf(12.3),
            String.valueOf(true),
            String.valueOf(new Object()),
        };

        for (String str: strings) {
            System.out.print(str);
            System.out.println(str instanceof String);
        }
    }

    /** 字符串转换为其他类型 */
    static void demo10 () {
        int n1 = Integer.parseInt("100");
        int n2 = Integer.parseInt("ff", 16); // 十六进制转十进制

        boolean b1 = Boolean.parseBoolean("true");
        boolean b2 = Boolean.parseBoolean("FAlse");

        System.out.println(b1);
        System.out.println(b2);
    }

    /** 获取系统变量  */
    static void demo11 () {
        System.out.println(Integer.getInteger("java.version"));
    }

    /** char[] String 互转*/
    static void demo12 () {
        char[] chars = "Hello".toCharArray();

        for (char ch: chars)  {
            System.out.println(ch);
        }

        String str = new String(chars); // 此处内部会复制一份

        chars[0] = 'h'; // 改变chars 不会改变 str
        System.out.println(str);
    }

    /** 实现一个自定义方法同demo12 的效果  */
    static void demo13 () {
        int[] scores = {100, 89, 98, 70};

        Scores s = new Scores(scores);
        s.print();

        scores[0] = 59; // 糟糕，成绩被篡改了
        s.print();
    }

    static void demo14 () {
        int[] scores = {100, 89, 98, 70};

        ScoresBlock s = new ScoresBlock(scores);
        s.print();

        scores[0] = 59; // 想改成绩，没门
        s.print();
    }

    /**
     * 不同编码之间转换
     * ASCII    一字节        0 - 127
     * Unicode  两个字节或以上
     * UTF-8   一到四字节变长编码（容错能力强）
     * */
    static void demo15 () {
        String str = "Hello中文";

        try {
            byte[] b1 = str.getBytes(); // 系统默认编码转换
            byte[] b2 = str.getBytes("UTF-8");
            byte[] b3 = str.getBytes("GBK");
            byte[] b4 = str.getBytes(StandardCharsets.UTF_8);

            System.out.println(Arrays.toString(b1));
            System.out.println(Arrays.toString(b2));
            System.out.println(Arrays.toString(b3));
            System.out.println(Arrays.toString(b4));
        } catch (UnsupportedEncodingException err) {
            System.out.println(err);
        }
    }

    /** StringBuilder */
    static void demo16 () {
        var sb = new StringBuilder(1024);

        sb.append("hello")
                .append("cungen")
                .append("!")
                .insert(0, "ann say:");
        System.out.println(sb.toString());
    }

    /** StringJoiner 除了可以加头尾，没有其他优势了*/
    static void demo17 () {
        var sj = new StringJoiner(":", "they say", "!");

        String[] strs = { "hello", "cungen" };

        for (String str : strs) {
            sj.add(str);
        }
        System.out.println(String.join(",", strs));
        System.out.println(sj.toString());
    }

    public static void main(String[] args) {
        // demo1();
        // demo2();
        demo17();
    }

}

class Scores {
    private int[] scores;

    public Scores (int[] scores) {
        this.scores = scores;
    }

    public void print () {
        System.out.println(Arrays.toString(scores));
    }
}

/* 不能被修改的成绩单 */
class ScoresBlock {
    private int[] scores;

    public ScoresBlock (int[] scores) {
        int len = scores.length;
        // int[] arr = new int[len];
        // for (int i = 0; i < len; i++) {
        //     arr[i] = scores[i];
        // }
        this.scores = Arrays.copyOf(scores, len);
    }

    public void print () {
        System.out.println(Arrays.toString(scores));
    }
}
