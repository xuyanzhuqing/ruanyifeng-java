import java.util.Arrays;
import java.util.Scanner;

public class ControlFlow {
    /**
     * 输出
     * */
    public static void demo1 () {
        System.out.println("welcome");

        System.out.print("H");
        System.out.print("E");
        System.out.print("L");
        System.out.print("L");
        System.out.print("O");

        System.out.println();
    }

    /**
     * 占位符
     * */
    public static void demo2 () {
        //    %d	格式化输出整数
        //    %x	格式化输出十六进制整数
        //    %f	格式化输出浮点数
        //    %e	格式化输出科学计数法表示的浮点数
        //    %s	格式化字符串

        int age = 18;

        System.out.format("我今年%d岁了", age);

        int n = 12345000;
        System.out.printf("n=%d, hex=%08x", n, n); // 注意，两个%占位符必须传入两个数

        double d = 3.1415926;
        System.out.printf("%.2f\n", d); // 显示两位小数3.14
        System.out.printf("%.4f\n", d); // 显示4位小数3.1416
    }

    /**
     * 输入
     *
     * 命令行执行方法
     * 1. javac ControlFlow.java
     * 2. java ControlFlow
     * */
    public static void demo3 () {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入你的名字");

        String name = scanner.nextLine();
        System.out.println("请输入你的年龄");

        int age = scanner.nextInt();

        System.out.printf("Hi，%s，you are %d\n", name, age);
    }

    /*
    * switch case
    * */
    public static void demo4 () {

        // 旧语法
        {
            int option = 3;

            switch (option) {
                case 1:
                    System.out.println("option 1 is selected");
                    break;
                case 2:
                    System.out.println("option 2 is selected");
                    break;
                case 3:
                case 4:
                    System.out.println("option is 3 or 4");
                    break;
                default:
                    System.out.println("can't be recognized");
                    break;
            }
        }

        // 新语法 - 不支持穿透
        {
            Scanner scanner = new Scanner(System.in);

            System.out.println("你想吃什么水果，苹果还是香蕉？");

            String fruit = scanner.nextLine();

            int code = switch (fruit) {
                case "苹果" -> {
                    System.out.println("苹果的英语是 apple");
                    yield 0;
                }
                case "香蕉" -> {
                    System.out.println("香蕉的英语是 banner");
                    yield 1;
                }
                default -> {
                    System.out.println("抱歉，没有这种水果");
                    yield -1;
                }
            };

            if (code == -1) {
                System.out.println("非法水果代码");
            } else {
                System.out.printf("%s水果代码是%d", fruit, code);
            }
        }
    }

    /*
    * 输入输出练习题
    *
    * 请帮小明同学设计一个程序，输入上次考试成绩（int）和本次考试成绩（int），然后输出成绩提高的百分比，保留两位小数位（例如，21.75%）。
    * */
    public static void example () {
        Scanner scanner = new Scanner(System.in);

        System.out.println("请输入上次考试成绩");
        int last = scanner.nextInt();

        System.out.println("请输入本次考试成绩");
        int current = scanner.nextInt();

        float rate = (float)(current - last) * 100 / last;

        System.out.printf("请继续努力，相较于上次，本次成绩提高了 %.2f", rate);
    }

    /*
    * 设计一个BMI 肥胖指数计算器
    *
    * BMI = 体重(kg) / 身高(m)的平方
    *
    *    过轻：低于18.5
    *    正常：18.5 ~ 25
    *    过重：25 ~ 28
    *    肥胖：28 ~ 32
    *    非常肥胖：高于32
    * 要点：浮点数比较
    * */
    public static void example1 () {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入你的体重(kg)");

        float weight = scanner.nextFloat();

        System.out.println("请输入你的身高(m)");

        float tall = scanner.nextFloat();

        float rate = weight / (tall * tall);

        System.out.printf("您的 BMI 指数是 %.2f", rate);

        if (rate > 32) {
            System.out.println("非常肥胖");
        } else if (rate <= 32 && rate >= 28) {
            System.out.println("肥胖");
        } else if (rate < 28 && rate >= 25) {
            System.out.println("过重");
        } else if (rate < 25 && rate >= 18.5) {
            System.out.println("正常");
        } else {
            System.out.println("过轻");
        }

    }

    /**
     * 小兔子开门
     * */
    public static void example2 () {
        Scanner scanner = new Scanner(System.in);
        System.out.println("小兔子乖乖，把门开开");
        String name = scanner.nextLine();

        // 引用类型比较，使用equals，确定不为 null 的放在前面，防止空指针
        if ("妈妈".equals(name)) {
            System.out.println("妈妈回来了");
        } else {
            System.out.println("大灰狼，不开不开");
        }
    }

    /*
    * 计算 1 - 100 的和
    * */
    public static void example3 () {
        int res = 0;
        int n = 1;
        while (n <= 100) {
            res += n;
            n++;
        }
        System.out.println(res);
    }

    // 给定一个数组，请用for循环倒序输出每一个元素：
    public static void example4 () {
        int[] ns = { 1, 4, 9, 16, 25 };

        for (int i = ns.length - 1; i >= 0; i--) {
            System.out.println(ns[i]);
        }

        System.out.println("foreach");
        for (int n : ns) {
            System.out.println(n);
        }

        // 打印数组
        System.out.println(ns.toString());
    }

    /**
     * 计算 PI
     *
     * PI / 4 = 1 - 1/3 + 1/5 - 1/7 + 1/9 ....
     */
    public static void example5 () {
        int n = 1;
        float ans = 0;
        int flag = 1;

        for (; n <= 10000;) {
            ans += (float)(flag * 1) / n;
            flag *= -1;
            n += 2;
        }

        float PI = ans * 4;

        System.out.printf("PI is %.6f", PI);
    }

    public static void bobbleSort () {
        int[] ns = { 28, 12, 89, 73, 65, 18, 96, 50, 8, 36 };
        // 排序前:
        System.out.println(Arrays.toString(ns));
        for (int i = 0; i < ns.length - 1; i++) {
            for (int j = 0; j < ns.length - i - 1; j++) {
                if (ns[j] < ns[j+1]) {
                    // 交换ns[j]和ns[j+1]:
                    int tmp = ns[j];
                    ns[j] = ns[j+1];
                    ns[j+1] = tmp;
                }
            }
        }
        // 排序后:
        System.out.println(Arrays.toString(ns));
    }

    public static void average () {
        // 用二维数组表示的学生成绩:
        int[][] scores = {
                { 82, 90, 91 }, // 学生甲的语数英成绩
                { 68, 72, 64 }, // 学生乙的语数英成绩
                { 95, 91, 89 }, // ...
                { 67, 52, 60 },
                { 79, 81, 85 },
        };

        double ans = 0;
        for (int arr[] : scores) {
            for (int score : arr) {
                ans += score;
                System.out.println(score);
            }
        }

        System.out.println(Arrays.deepToString(scores));

        // TODO:
        double average = (double)ans / (scores.length * scores[0].length);
        System.out.println(average);
        if (Math.abs(average - 77.733333) < 0.000001) {
            System.out.println("测试成功");
        } else {
            System.out.println("测试失败");
        }
    }

    public static void main(String[] args) {
       // demo1();
       // demo2();
       // demo3();
       // demo4();
       // example();
       // example1();
       // example2();
       // example3();
       // example5();
       // bobbleSort();
       // average();

        // 打印命令行参数
        for (String arg: args) {
            System.out.println(arg);
            if ("-version".equals(arg)) {
                System.out.println("v1.0.0");
                break;
            }
        }
    }

}
