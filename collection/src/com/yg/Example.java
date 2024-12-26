package com.yg;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringJoiner;

public class Example {
  public static void main(String[] args) {
    autoRun(Example.class);
  }

  /** 测试栈 */
  static void demo1() {
    increase(1); // StackOverflowError
  }

  static int increase(int x) {
    return increase(x) + 1;
  }

  /** 计算12500的 16 进制表示 */
  static void demo2() {
    Deque<String> stack = new LinkedList<>();

    int n = 12500;
    while (n != 0) {
      int r = n % 16;
      n /= 16;
      stack.push(Integer.toString(r, 16));
    }

    StringJoiner joiner = new StringJoiner("", "#", "");

    System.out.println(Arrays.toString(stack.toArray()));
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
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
