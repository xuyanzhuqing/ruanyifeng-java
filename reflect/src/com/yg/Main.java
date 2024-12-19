package com.yg;

// 反射
// 什么是反射，反射就是通过实例获取所在类的全部信息的方法就是反射

import java.lang.reflect.*;

public class Main {
    public static void main(String[] args) {
        autoRun(Main.class);
    }

    /** 如何获取一个class的 Class实例 */
    static void demo1 () {
        // 三种方式
        Class cls1 = String.class;

        Class cls2 = "cungen".getClass();

        Class cls3 = null;
        try {
            String className = "java.lang.String";
            cls3 = Class.forName(className);
        } catch (ClassNotFoundException e) {

        }

        // 获取到的类的实例，是唯一的
        System.out.println(cls1 == cls2);
        System.out.println(cls3 == cls2);
    }

    /** == vs instanceof */
    static void demo2 () {
        Integer n = new Integer(123);

        boolean b1 = n instanceof Integer; // true，因为n是Integer类型
        boolean b2 = n instanceof Number; // true，因为n是Number类型的子类

        boolean b3 = n.getClass() == Integer.class; // true，因为n.getClass()返回Integer.class
        Class c1 = n.getClass();
        Class c2 = Number.class;
        boolean b4 = c1 == c2; // false，因为Integer.class != Number.class
    }

    /** 打印不同类 */
    static void demo3 () {
        printClassInfo("".getClass());
        printClassInfo(Runnable.class);
        printClassInfo(java.time.Month.class);
        printClassInfo(String[].class); // 注意数组也有也有单独的类 [Ljava.lang.String
        printClassInfo(int.class);
    }

    /** 通过Class 实例创建类, 只能调用public无参构造方法 */
    static void demo4 () throws InstantiationException, IllegalAccessException {
        Class cls = "".getClass();

        String s = (String) cls.newInstance();
    }

    /** 运行时动态加载类 */
    static void demo5 () {
        // LogFactory factory = null;
        // if (isClassPresent("org.apache.logging.log4j.Logger")) {
        //     factory = createLog4j();
        // } else {
        //     factory = createJdkLog();
        // }
    }

    /** 访问字段 */
    static void demo6 () {
        Person ann = new Person(18, "ann");
        Class p = ann.getClass();

        try {
            Field name = p.getField("name"); // 包括父类

            Field age = p.getDeclaredField("age"); // 不包括父类, 注意私有字段只能用 getDeclaredField 访问
            // 通过反射设置 name
            name.set(ann, "meinv");

            // 通过反射设置私有字段
            age.setAccessible(true);
            age.set(ann, 30);

            System.out.println(ann.getName());
            System.out.println(ann.getAge());

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /** 访问字段 */
    static void demo7 () {
        Student ann = new Student(18, "ann", 110);
        Class p = ann.getClass();

        try {
            Field name = p.getField("name"); // 包括父类

            Field number = p.getDeclaredField("number"); // 不包括父类, 注意私有字段只能用 getDeclaredField 访问
            // 通过反射设置 name
            name.set(ann, "meinv");

            // 通过反射设置私有字段
            number.set(ann, 119);

            System.out.println(ann.getName());
            System.out.println(ann.getAge());
            System.out.println(ann.getNumber());

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    
    /** 实例方法 */
    static void demo8 () {
        try {
            Class stdClass = Person.class;
            // 获取public方法getScore，参数为String:
            System.out.println(stdClass.getMethod("setName", String.class));
            // 获取继承的public方法getName，无参数:
            System.out.println(stdClass.getMethod("getName"));
            // 获取private方法getGrade，参数为int:
            System.out.println(stdClass.getDeclaredMethod("setAge", int.class));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    /** 调用反射回来的方法  */
    static void demo9 () {
        try {
            // String对象:
            String s = "Hello world";
            // 获取String substring(int)方法，参数为int:
            Method m = String.class.getMethod("substring", int.class);
            // 在s对象上调用该实例方法并获取结果:
            String r = (String) m.invoke(s, 6);
            // 打印调用结果:
            System.out.println(r); // "world"

            // getName()：返回方法名称，例如："getScore"；
            // getReturnType()：返回方法返回值类型，也是一个Class实例，例如：String.class；
            // getParameterTypes()：返回方法的参数类型，是一个Class数组，例如：{String.class, int.class}；
            // getModifiers()：返回方法的修饰符，它是一个int，不同的bit表示不同的含义。
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /** 多态 */
    static void demo10 () throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        class Animal {
            public void sound () {
                System.out.println("....");
            }
        }

        class Duck extends Animal {
            @Override
            public void sound() {
                System.out.println("嘎嘎嘎嘎");
            }
        }

        Class cls = Animal.class;
        Method method = cls.getDeclaredMethod("sound");

        method.invoke(new Duck()); // 鸭子叫，嘎嘎嘎嘎
    }

    /** 构造方法 */
    static void demo11 () throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        // 只能调用无参构造方法
        // Student xiaoming = Student.class.newInstance();
        // xiaoming.setAge(18);
        // System.out.println(xiaoming.getAge());

        Constructor<Student> fullConstructor = Student.class.getConstructor(int.class, String.class, int.class);

        Student cungen = fullConstructor.newInstance(18, "cungen", 120);
        System.out.println(cungen.name);
    }

    /** 获取父类，接口 */
    static void demo12 () {
        Class cls = Integer.class;

        while (cls != null) {
            System.out.println(cls.getName());
            cls = cls.getSuperclass();
        }

        Class[] is = Integer.class.getInterfaces();
        for (Class i : is) {
            System.out.println(i.getName());
        }

        // 判断继承关系
        Object n = new Integer(1);
        System.out.println(n instanceof Number);

        System.out.println(Number.class.isAssignableFrom(Integer.class)); // Integer 可以向上转型为Number
        System.out.println(Integer.class.isAssignableFrom(Number.class)); // Number 不可以向上转型为Integer
    }
    
    /** 动态代理
     * 不编写实现类，直接在运行期创建某个interface的实例
     * */
    static void demo13 () throws Throwable {
        InvocationHandler handler = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println(method);
                if (method.getName().equals("morning")) {
                    // 真正的实现
                    System.out.println("good morning " + args[0]);
                }
                return null;
            }
        };

        Hello hello = (Hello) Proxy.newProxyInstance(
            Hello.class.getClassLoader(),
            new Class[] { Hello.class },
            handler
        );

        hello.morning("blob");

        // 以上动态类的静态类版本

        // class HelloDynamicProxy implements Hello {
        //     InvocationHandler handler;
        //     public HelloDynamicProxy(InvocationHandler handler) {
        //         this.handler = handler;
        //     }
        //     public void morning(String name) throws Throwable {
        //         handler.invoke(
        //                 this,
        //                 Hello.class.getMethod("morning", String.class),
        //                 new Object[] { name }
        //         );
        //     }
        // }
    }
    /** 静态方法 */
    static void autoRun (Class cls) {
        try {
            Method[] methods  = cls.getDeclaredMethods();
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

    static boolean isClassPresent(String name) {
        try {
            Class.forName(name);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    static void printClassInfo(Class cls) {
        System.out.println("Class name: " + cls.getName());
        System.out.println("Simple name: " + cls.getSimpleName());
        if (cls.getPackage() != null) {
            System.out.println("Package name: " + cls.getPackage().getName());
        }
        System.out.println("is interface: " + cls.isInterface());
        System.out.println("is enum: " + cls.isEnum());
        System.out.println("is array: " + cls.isArray());
        System.out.println("is primitive: " + cls.isPrimitive());
    }
}

class Student extends Person {
    // 学号
    public int number;

    public Student(int age, String name, int number) {
        super(age, name);
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}

class Person {
    private int age;
    public String name;

    public Person(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

interface Hello {
    void morning(String name) throws Throwable;
}