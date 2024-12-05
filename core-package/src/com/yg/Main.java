package com.yg;

public class Main {
    /** 装箱/拆箱  */
    static void demo1 () {
        // 包装类的实质，只发生在编译阶段
        class Integer {
            private int value;

            public Integer(int value) {
                this.value = value;
            }

            public int intValue() {
                return this.value;
            }
        }

        // 这就是装箱
        Integer n = null;
        Integer n2 = new Integer(100);
    }

    /** 装箱*/
    static void demo2 () {
        int i = 100;
        // 通过new操作符创建Integer实例(不推荐使用,会有编译警告):
        Integer n1 = new Integer(i);
        // 通过静态方法valueOf(int)创建Integer实例: 推荐
        Integer n2 = Integer.valueOf(i);
        // 通过静态方法valueOf(String)创建Integer实例:
        Integer n3 = Integer.valueOf("100");
        System.out.println(n3.intValue());
    }

    /** 自动拆包 */
    static void demo3 () {
        int n = Integer.valueOf("100");

        // 注意注意注意，自动拆箱可能报空指针异常
        // Integer n1 = null;
        // int n2 = Integer.valueOf(n1);
    }
    
    /** 所有的包装类型都是不变类 */
    static void demo4 () {

        // 内部实现类似
        final class Integer {
            private final int value;
            public Integer (int value) {
                this.value = value;
            }
        }
    }

    /** 引用类型，必须使用equals()比较 */
    static void demo5 () {
        Integer x = 127;
        Integer y = 127;
        Integer m = 99999;
        Integer n = 99999;
        System.out.println("x == y: " + (x==y)); // 小数 true Integer.valueOf()对于较小的数，始终返回相同的实例
        System.out.println("m == n: " + (m==n)); // 大数 false
        System.out.println("x.equals(y): " + x.equals(y)); // true
        System.out.println("m.equals(n): " + m.equals(n)); // true
    }

    /* 进制转换 */
    static void demo6() {
        int x1 = Integer.parseInt("100"); // 100
        int x2 = Integer.parseInt("100", 16); // 256,因为按16进制解析
        System.out.println(x1);
        System.out.println(x2);

        System.out.println(Integer.toString(100)); // "100",表示为10进制
        System.out.println(Integer.toString(100, 36)); // "2s",表示为36进制
        System.out.println(Integer.toHexString(100)); // "64",表示为16进制
        System.out.println(Integer.toOctalString(100)); // "144",表示为8进制
        System.out.println(Integer.toBinaryString(100)); // "1100100",表示为2进制
    }

    /** 包装类的静态常量 */
    static void demo7 () {
        // boolean只有两个值true/false，其包装类型只需要引用Boolean提供的静态字段:
        Boolean t = Boolean.TRUE;
        Boolean f = Boolean.FALSE;
        // int可表示的最大/最小值:
        int max = Integer.MAX_VALUE; // 2147483647
        int min = Integer.MIN_VALUE; // -2147483648
        // long类型占用的bit和byte数量:
        int sizeOfLong = Long.SIZE; // 64 (bits)
        int bytesOfLong = Long.BYTES; // 8 (bytes)
    }

    /**  */
    static void example () {
        // 向上转型为Number:
        Number num = 999;
        // 获取byte, int, long, float, double:
        byte b = num.byteValue();
        int n = num.intValue();
        long ln = num.longValue();
        float f = num.floatValue();
        double d = num.doubleValue();
    }
    
    /** 处理无符号整型 */
    static void demo8 () {
        byte x = -1; // 计算机中是采用补码计算数字
        /*
        * -1
        * 原码 1000_0001
        * 反码 1111_1110
        * +1
        * 补码 1111_1111
        * */
        byte y = 127;
        System.out.println(Byte.toUnsignedInt(x)); // 255
        System.out.println(Byte.toUnsignedInt(y)); // 127
    }

    public static void main(String[] args) {
        demo6();
    }
}

