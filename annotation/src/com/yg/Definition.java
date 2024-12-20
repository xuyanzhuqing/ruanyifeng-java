package com.yg;

import java.lang.annotation.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
// 总结：必须设置@Target和@Retention，@Retention一般设置为RUNTIME，因为我们自定义的注解通常要求在运行期读取。一般情况下，不必写@Inherited和@Repeatable。

public class Definition {
}


// 类或接口：ElementType.TYPE
// 字段：ElementType.FIELD
// 方法：ElementType.METHOD
// 构造方法：ElementType.CONSTRUCTOR
// 方法参数：ElementType.PARAMETER
@Target/*元注解*/(ElementType.METHOD/*可以应用到源码的方法上*/)
// 仅编译期：RetentionPolicy.SOURCE
// 仅class文件：RetentionPolicy.CLASS
// 运行期：RetentionPolicy.RUNTIME（常用）
@Retention(RetentionPolicy.RUNTIME)
@Inherited // 子类是否自动继承父类
@interface Report {
    int type() default 0;
    String level() default "info";
    String value() default "";
}

@Target({ // 数组形式
        ElementType.METHOD,
        ElementType.FIELD
})
@interface Report1 {
    int type() default 0;
    String level() default "info";
    String value() default "";
}


@Target(ElementType.TYPE)
@interface Reports {
    Report2[] value();
}

@Repeatable(Reports.class)
@Target(ElementType.TYPE)
@interface Report2 {
    int type() default 0;
    String level() default "info";
    String value() default "";
}


