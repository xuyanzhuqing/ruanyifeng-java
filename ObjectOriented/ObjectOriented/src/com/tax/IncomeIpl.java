package com.tax;


// 抽象类和接口的对比如下：
// abstract class	        interface
// 继承	只能extends一个class	可以implements多个interface
// 字段	可以定义实例字段	    不能定义实例字段
// 抽象方法	可以定义抽象方法	可以定义抽象方法
// 非抽象方法	可以定义非抽象方法	可以定义default方法

// 接口
interface IncomeInter {
    public void testInterface ();

    /* 只能定义default 方法， 不能访问实例字段 */
    public default void run () {
    }
}

abstract public class IncomeIpl implements IncomeInter {
    // 我是抽象类，我可以被定义
    public final String usage = "取之于明，用之于民";

    public abstract double getTax();

    public abstract double doubleTax();
}
