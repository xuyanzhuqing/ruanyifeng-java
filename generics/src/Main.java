import java.beans.JavaBean;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
// 泛型

public class Main {
    public static void main(String[] args) {
        autoRun(Main.class);
    }

    static void demo1() {
        // ArrayList<Integer>  -->  List<Integer> 可以转型成功
        // ArrayList<Integer>  -/-> ArrayList<Number> 不可以转型成功 float 也是 number 的子类
    }

    /** 使用泛型 */
    static void demo2 () {
        List<Number> list = new ArrayList<Number>();
        List<Number> list1 = new ArrayList<>(); // 等价
    }
    
    /** 排序 */
    static void demo3 () {
        Person p1 = new Person("ann", 100);
        Person p2 = new Person("cungen", 99);

        Person[] persons = new Person[] {p1, p2};
    }

    static void autoRun(Class cls) {
        try {
            Method[] methods = cls.getDeclaredMethods();
            int max = 0;
            for (int i = 0; i < methods.length; i++) {
                String name = methods[i].getName();
                if (name.startsWith("demo")) {
                    int index = Integer.parseInt(name.replace("demo", ""));
                    max = Math.max(index, max);
                }
            }
            Method method = cls.getDeclaredMethod("demo" + max);
            method.setAccessible(true);
            // 执行静态方法
            method.invoke(null);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}

// 不可排序的对象
class Person {
    String name;
    int score;

    public Person(String name, int score) {
        this.name = name;
        this.score = score;
    }

    @Override
    public String toString() {
        return name + ":" +  score;
    }
}

// 可排序的对象
class PersonComparable implements Comparator<PersonComparable> {
    String name;
    int score;

    public PersonComparable(String name, int score) {
        this.name = name;
        this.score = score;
    }

    @Override
    public String toString() {
        return name + ":" +  score;
    }

    @Override
    public int compare(PersonComparable o1, PersonComparable o2) {
        if (o1.score == o2.score) return 0;
        return o1.score > o2.score ? 1 : -1;
    }
}