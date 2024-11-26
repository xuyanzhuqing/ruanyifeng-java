/**
 * 常识：
 * java 中最小单位是字节，1 字节等于 8 bit
 *           -2^8n ~ 2^8n-1
 * byte   1  -128 ~ 127
 * short  2  -32768 ~ 32767
 * int    4
 * long   8
 * float  4
 * double 8
 * char   2
 *
 * String
 *
 * var
 */

public class Variable01 {
    public static void demo1 () {
        /* 变量类型 */
        // 基本类型
        byte age = 127;
        short earth = 32767;
        int houses = 100_000_000;
        long stars = 1_000_000_000;
        long sands = 1_000_000_000l;
        float money = 100.01f;
        double money1 = 1_000_000_000.00;

        boolean isMarried = true;
        boolean isAdult = age > 18;

        // 注意注意，这里使用单引号包括
        char a = 'A';

        // 引用类型
        String welcome = "welcome, welcome";

        // 常量
        final double PI = 3.14;

        // var 关键字, 自动装包
        var sb = new StringBuilder();
        // 两者等效
        StringBuilder sb1 = new StringBuilder();
    }

    /**
    * 整数运算
    *
    * 四则运算（+ - * /）
    * 自增/减(++, --)，
    * 位运算(&, |, ^, ~)，
    * 移位运算（<<， >> ）左移乘2，右移除2，
    * 无符号移动 (>>>) 不管符号位，右移后高位总是补0
    * */
    public static void demo2 () {
        {
            int x = 2147483640;
            int y = 15;
            int sum = x + y;
            // 转成2进制相加，位溢出，高位变成1，成了负数
            System.out.println(sum); // -2147483641
        }
        {
            // 溢出的解决方案
            // 将int 转化为 long
            long x = 2147483640;
            int y = 15;
            long sum = x + y; // 注意这里会先进行隐式转换，将 y 转成 long

            System.out.println(sum); // -2147483641
        }

        // 注意注意，从大范围整数转为小范围时，可能出错，
        // 高位字节会被直接扔掉，仅保留了低位的字节：
        {
            int i1 = 1234567;
            short s1 = (short) i1; // -10617
            System.out.println(s1);
            int i2 = 12345678;
            short s2 = (short) i2; // 24910
            System.out.println(s2);
        }
    }

    /**
     * 浮点数运算
     *
     * 四则运算（+ - * /）
     * 不能做位运算和移位运算
     *
     * 表示数字的范围大，但是常常不能很精确表达数字
     */
    public static void demo3 () {
        // 浮点数的误差
        // 0.1 是一个无限循环小数，无论float 还是 double都不能精确表达，只能提高表达精度
        // 0.5 可以精确表达
        {
            double x = 1.0 / 10;
            double y = 1 - 9.0 / 10;
            // 观察x和y是否相等:
            System.out.println(x);
            System.out.println(y);
        }

        // 由于浮点数存在运算误差，所以比较两个浮点数是否相等常常会出现错误的结果。正确的比较方法是判断两个浮点数之差的绝对值是否小于一个很小的数：
        {
            double x = 1.0 / 10;
            double y = 1 - 9.0 / 10;
            // 比较x和y是否相等，先计算其差的绝对值:
            double r = Math.abs(x - y);
            // 再判断绝对值是否足够小:
            if (r < 0.00001) {
                // 可以认为相等
            } else {
                // 不相等
            }
        }

        // 类型提升
        {
            int n = 5;
            double d = 1.2 + 24 / n; // 结果不是 6.0 而是 5.2
            System.out.println(d);
            // 乘除的优先级高于加法，24 / n 计算的结果是 4
        }

        // 解决办法
        {
            // double d = 1.2 + 24.0 / n; // 结果是 6.0
        }

        // 浮点数溢出
        {
            double d1 = 0.0 / 0; // NaN
            double d2 = 1.0 / 0; // Infinity
            double d3 = -1.0 / 0; // -Infinity

            System.out.println(d1);
            System.out.println(d2);
            System.out.println(d3);
        }

        // 强制转型
        {
            int n1 = (int) 12.3; // 12
            int n2 = (int) 12.7; // 12
            int n3 = (int) -12.7; // -12

            // 转型四舍五入的小技巧
            int n4 = (int) (12.7 + 0.5); // 13
            int n5 = (int) 1.2e20; // 2147483647
        }
    }

    /**
     * 布尔运算
     *
     * 比较运算符 > >= < <= == !=
     * 与或非 && || !
     * */
    public static void demo4 () {
        //    关系运算符的优先级从高到低依次是：
        //    !
        //    >，>=，<，<=
        //    ==，!=
        //    &&
        //    ||

        boolean isGreater = 5 > 3; // true
        int age = 12;
        boolean isZero = age == 0; // false
        boolean isNonZero = !isZero; // true
        boolean isAdult = age >= 18; // false
        boolean isTeenager = age >6 && age <18; // true

        // 短路运算
        {
            boolean b = 5 < 3;
            boolean result = b && (5 / 0 > 0); // 此处 5 / 0 不会报错
            System.out.println(result);
        }

        // 三元运算
        {
            int n = -100;
            int x = n >= 0 ? n : -n;
            System.out.println(x);
        }
    }

    /** 字符 和 字符串 */
    public static void demo5 () {
        char c1 = 'A';
        char c2 = '中';

        // 显示一个字符的Unicode编码
        int n1 = 'A'; // 字母“A”的Unicodde编码是65
        int n2 = '中'; // 汉字“中”的Unicode编码是20013

        // 注意是十六进制:
        char c3 = '\u0041'; // 'A'，因为十六进制0041 = 十进制65
        char c4 = '\u4e2d'; // '中'，因为十六进制4e2d = 十进制20013

        // 单行字符串
        {
            String s = ""; // 空字符串，包含0个字符
            String s1 = "A"; // 包含一个字符
            String s2 = "ABC"; // 包含3个字符
            String s3 = "中文 ABC"; // 包含6个字符，其中有一个空格
        }
        // 转译字符
        // \' \" \\ \n \r \t（十六进制）
        // 多行字符串
        {
            String s = """
           SELECT * FROM
             users
           WHERE id > 100
           ORDER BY name DESC
           """;
        }
    }

    /**
     * 数组
     *
     * */
    public static void demo6 () {
        // 数组的初始化
        {
            var arr = new int[5];
            arr[0] = 1;
            arr[1] = 2;
            arr[2] = 2;
            arr[3] = 2;
            arr[4] = 2;

            int[] arr1 = new int[] {1, 2, 3, 4, 5};

            int[] ns = {1, 2, 3, 4, 5};
        }
    }

    /* 计算前N个自然数的和 */
    public static void example () {
        int n = 100;

        int sum = n * (1 + n) / 2;

        System.out.println(sum);
        System.out.println(sum == 5050 ? "测试通过" : "测试失败");
    }

    // 根据求根公式求解
    public static void example1 () {
        double a = 1.0;
        double b = 3.0;
        double c = -4.0;

        double delta = Math.sqrt(b*b - 4*a*c);
        double r1 = (-b + delta)/2*a;
        double r2 = (-b - delta)/2*a;
        System.out.println(r1);
        System.out.println(r2);
        System.out.println(r1 == 1 && r2 == -4 ? "测试通过" : "测试失败");
    }

    // 请将下面一组int值视为字符的Unicode码，把它们拼成一个字符串：
    public static void example2 () {
        int a = 72;
        int b = 105;
        int c = 65281;
        // FIXME:
        String s = "" + (char)a + (char)b + (char)c; // Hi!
        System.out.println(s);
    }

    public static void main(String[] args) {
        System.out.println("variable 01");
        demo2();
        demo3();
        example();
        example1();
        example2();
    }
}
