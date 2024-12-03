import com.shape.Rect;
import com.shape.Shape;
import com.tax.Income;
import com.tax.RoyaltyIncome;
import com.tax.SalaryIncome;

import java.util.Scanner;

public class ObjectOriented {
    public void demo1 () {
        City beijing = new City();
        beijing.name = "北京";
        beijing.latitude = 39.906217f;
        beijing.longitude = 116.3912757f;

        System.out.println(beijing);
    }

    // 私有变量
    public static void demo2 () {
        Scanner scanner = new Scanner(System.in);

        Person p = new Person();
        System.out.println("请输入名称:");
        String  name = scanner.nextLine();

        System.out.println("请输入年龄:");
        int age = scanner.nextInt();

        p.setName(name);
        p.setAge(age);

        System.out.println(p);
    }

    // 私有方法
    public static void demo3 () {
        Scanner scanner = new Scanner(System.in);

        System.out.println("请输入名称:");
        String  name = scanner.nextLine();

        System.out.println("请输入出生年份:");
        int age = scanner.nextInt();

        var p = new Lady(name, age);
        System.out.println(p);
    }

    // 基本类型值拷贝，引用类型值引用
    public static void demo4 () {
        var name = "lover";
        var fee = 100;
        Lady[] ladies = { new Lady("ann", 1994), new Lady("胡燕", 1993) };

        var groups = new Groups(name, ladies);
        groups.setFee(fee);

        System.out.println(groups);
        System.out.println("-----a few moments later--------");

        name = "前女友们"; // 数值不会改变（String 类型因为不可变）
        fee = 101;  // 数值不会改变

        ladies[0].currentYear = 2023;  // 引用值会改变
        System.out.println(groups);
    }

    /* 构造方法 */
    public static void demo5 () {
        Lady ann = new Lady("ann", 1994);
        Lady anna = new Lady("anna");
        Lady gg = new Lady();

        System.out.println(ann);
        System.out.println(anna);
        System.out.println(gg);
    }

    /* 方法重载 */
    public static void demo6 () {
        Lady gg = new Lady();
        gg.callme();

        gg.callme(2);
        gg.callme(60);
        gg.callme(61);
    }

    /* 继承 */
    public static void demo7 () {
        Student s = new Student(100, "ann", 18);
        s.setAge(30);
        System.out.println(s);
    }

    /* 转型 */
    public static void demo8 () {
       // 向上转型
        Shape s = new Rect();
        s.setHeight(10);
        System.out.println(s);

        // 向下转型， 可能会失败
        Shape s1 = new Shape();
        // Rect r = (Rect) new Shape(); // 会失败
        // Rect r1 = (Rect) s; // 会成功

        // 万无一失的向下转型的方法
        if (s1 instanceof Rect) {
            Rect s1_1 = (Rect) s1;
            System.out.println("转型成功");
            System.out.println(s1_1);
        }

        // 与上面等价
        if (s1 instanceof Rect s1_1) {
            System.out.println("转型成功");
            System.out.println(s1_1);
        }
    }

    /* 计算个税 */
    public static void demo9 () {
        Income[] incomes = { new Income(1000), new SalaryIncome(20000), new RoyaltyIncome(8000)};
        double tax = 0;

        for (Income income : incomes) {
            tax += income.getTax();
        }
        System.out.println("您应当纳税" + tax);
    }

    public static void demo10 () {
        var a = new Student(100, "ann", 18);
        var b = new Student(99, "michael", 20);
    }

    public static void main(String[] args) {
        demo10();
    }
}
