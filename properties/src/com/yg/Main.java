package com.yg;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Properties;
import java.util.StringJoiner;

// 读取配置，加载配置
// Java集合库提供的Properties用于读写配置文件.properties。.properties文件可以使用UTF-8编码；
//
// 可以从文件系统、classpath或其他任何地方读取.properties文件；
//
// 读写Properties时，注意仅使用getProperty()和setProperty()方法，不要调用继承而来的get()和put()等方法。

public class Main {

    public static void main(String[] args) {
        autoRun(Main.class);
    }

    /** 读取配置文件 */
    static void demo1() throws IOException {
        File f = new File("src/.properties");

        System.out.println(f.getAbsolutePath());
        Properties props = new Properties();
        props.load(new java.io.FileInputStream(f));

        String filepath = props.getProperty("last_open_file");
        String interval = props.getProperty("auto_save_interval", "120");
        String notDefined = props.getProperty("not_defined", "haha,你只能用我");

        System.out.println(filepath);
        System.out.println(interval);
        System.out.println(notDefined);
    }

    /** 读取jar包中的配置 */
    static void demo2 () throws IOException {
        Properties props = new Properties();
        InputStream input = Main.class.getResourceAsStream("../../.properties");

        System.out.println(input);
        props.load(input);
        System.out.println(props.getProperty("last_open_file"));
    }

    /** 读取内存中的配置 */
    static void demo3 () throws IOException {
        StringJoiner joiner = new StringJoiner("\n");
        joiner
                .add("# 这是一行注释")
                .add("who=cungen")
                .add("age=18");
        ByteArrayInputStream input = new ByteArrayInputStream(joiner.toString().getBytes("UTF-8"));

        Properties props = new Properties();
        props.load(input);

        String who = props.getProperty("who");
        String age = props.getProperty("age");

        System.out.println(who);
        System.out.println(age);
    }

    /** 写入配置 */
    static void demo4 () throws IOException {
        StringJoiner joiner = new StringJoiner("\n");

        joiner
                .add("# 这是一行注释")
                .add("last_open_file=/data/hi.txt")
                .add("auto_save_interval=30");
        ByteArrayInputStream input = new ByteArrayInputStream(joiner.toString().getBytes("UTF-8"));

        Properties props = new Properties();
        props.load(input);

        String dest = "src/./dest.properties";
        props.store(new FileOutputStream(dest), "new properties");
    }

    /** 加载多个配置文件
     *
     * 后面的会覆盖前面的
     * */
    static void demo5 () throws IOException {

        File[] files = new File[]{
            new File("src/.properties"),
            new File("src/dest.properties")
        };

        Properties props = new Properties();

        for (File file : files) {
            props.load(new FileInputStream(file)); // 字节流
            props.load(new FileReader(file, StandardCharsets.UTF_8)); // 字符流默认是ASCII 值，需要指定编码
        }

        System.out.println(props.getProperty("last_open_file"));
        System.out.println(props.getProperty("auto_save_interval"));
        System.out.println(props.getProperty("not_defined")); // 没有定义的是null
    }
    @SuppressWarnings("unchecked")
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
            if (method == null) {
                throw new NoSuchMethodException("hava not");
            }

            method.setAccessible(true);
            // 执行静态方法
            method.invoke(null);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @SuppressWarnings("unchecked")
    static void autoRun(Class cls, int which) {
        try {
            Method method = cls.getDeclaredMethod("demo" + which);
            if (method == null) {
                throw new NoSuchMethodException("hava not");
            }
            method.setAccessible(true);
            // 执行静态方法
            method.invoke(null);
        }  catch (Exception e) {
            e.printStackTrace();
        }
    }
}


