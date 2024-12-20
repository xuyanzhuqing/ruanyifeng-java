package com.yg;

import java.lang.annotation.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.StringJoiner;

public class Main {
    public static void main(String[] args) {
        autoRun(Main.class);
    }

    // 判断注解是否存在
    static void demo1() {
        // Class.isAnnotationPresent(Class)
        // Field.isAnnotationPresent(Class)
        // Method.isAnnotationPresent(Class)
        // Constructor.isAnnotationPresent(Class)
    }

    /** 使用注解 */
    static void demo2 () throws IllegalAccessException {
        Person p1 = new Person("wangningning", "黄山");
        Person p2 = new Person("cungen", "安康");
        Person p3 = new Person("guo", "安康");

        check(new Person[]{p1, p2});
        check(p1, p2, p3);
    }

    static void check (Person... persons) throws IllegalAccessException {
        for (Person p : persons) {
            Class cls = p.getClass();
            Field[] fields = cls.getDeclaredFields();

            for (Field f: fields) {
                Range range = f.getAnnotation(Range.class);
                if (range == null) {
                    continue;
                }
                Object value = f.get(p);
                if (value instanceof String s) {
                    if (s.length() < range.min() || s.length() > range.max()) {
                        throw new IllegalArgumentException(value + ":" + f.getName() + "参数不合法");
                    }
                }
            }
        }
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

// 使用注解
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface Range {
    int min() default  3;
    int max() default 5;
}

class Person {
    @Range(min=1, max=6)
    public String name;

    @Range(min = 2, max=10)
    public String city;

    public Person(String name, String city) {
        this.name = name;
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        var builder = new StringJoiner("-", "大家好，我要开始装逼了", "装逼结束");
        builder.add(name).add(city);
        System.out.println(builder);
        return builder.toString();
    }
}