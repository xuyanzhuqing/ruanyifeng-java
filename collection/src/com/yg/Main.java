package com.yg;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/* https://blog.csdn.net/youxijishu/article/details/135350055 代码格式化 */

/**
 * Map HashMap SortMap TreeMap
 *
 * <p>Set HashSet SortSet TreeSet
 */
public class Main {
  public static void main(String[] args) {
    autoRun(Main.class);
  }

  static void demo1() {
    /**
     * collect 是除map外其他类的根接口 list ArrayList linkedList set map
     *
     * <p>访问方式是统一的接口 Iterator<E>
     */

    /**
     * 过时的 Hashtable：一种线程安全的Map实现； Vector：一种线程安全的List实现； Stack：基于Vector实现的LIFO的栈。
     *
     * <p>Enumeration<E>：已被Iterator<E>取代。
     */
  }

  /** list 初始化 */
  static void demo2() {
    String[] strs = new String[] {"ann", "anna"};
    {
      List<String> list = new ArrayList<>();
      for (String str : strs) {
        list.add(str);
      }
    }
    {
      List<String> list = List.of("ann", "anna");
    }
    //                                                      list
    //        to                      array
    {
      List<Integer> list = List.of(1, 2);
      Object[] arr1 = list.toArray(); // 丢失类型
      Integer[] arr = list.toArray(Integer[]::new); // 终极解决大法
    }
  }

  /** 给定一组连续的整数，例如：10，11，12，……，20，但其中缺失一个数字，试找出缺失的数字： */
  static void demo3() {
    //                                                      构造从start到end的序列：
    final int start = 10;
    final int end = 20;
    List<Integer> list = new ArrayList<>();
    for (int i = start; i <= end; i++) {
      list.add(i);
    }
    //                                                      随机删除List中的一个元素
    //        :
    int removed = list.remove((int) (Math.random() * list.size()));
    int found = findMissingNumber(start, end, list);
    System.out.println(list.toString());
    System.out.println("missing number: " + found);
    System.out.println(removed == found ? "测试成功" : "测试失败");
  }

  /** 乱序中找出缺失的数字 */
  static void demo4() {
    System.out.println("乱序查找");
    //                                                      构造从start到end的序列：
    final int start = 10;
    final int end = 20;
    List<Integer> list = new ArrayList<>();
    for (int i = start; i <= end; i++) {
      list.add(i);
    }
    //                                                      洗牌算法shuffle可以随机交换List中的元素位置
    //        :
    Collections.shuffle(list);
    //                                                      随机删除List中的一个元素
    //        :
    int removed = list.remove((int) (Math.random() * list.size()));
    int found = findMissingNumber1(start, end, list);
    System.out.println(list.toString());
    System.out.println("missing number: " + found);
    System.out.println(removed == found ? "测试成功" : "测试失败");
  }

  /** list 内部采用equals 比较对象 */
  static void demo5() {
    List<String> list = List.of("A", "B", "C");
    System.out.println(list.get(2) == "C"); // 字符串不可变，内存地址相同
    System.out.println(list.get(2) == new String("C")); // 比较的是不同的对象
    //                                                      list
    //        内部采用equals              比较对象
    System.out.println(list.contains("C")); //                      true
    System.out.println(list.contains(new String("C"))); // true
    System.out.println(list.contains("X")); //                      false
    System.out.println(list.indexOf("C")); //                      2
    System.out.println(list.indexOf("X")); //                      -1
  }

  /** 编写一个equals 方法 自反性 对称性 传递性 一致性 null 与null 比较永远为 false */
  static void demo6() {
    class Person {
      String firstName;
      String lastName;
      int age;

      public Person(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
      }

      @Override
      //                                                      用于判断对象的是否相等，在hashcode
      //          相等时，作为唯一区分对象的方法
      public boolean equals(Object obj) {
        if (obj instanceof Person o) {
          return o.firstName.equals(firstName) && o.lastName.equals(lastName) && o.age == age;
        }
        return false;
      }

      @Override
      //                                                      用于在计算在map中的存储key
      public int hashCode() {
        //                                                      一般的计算方法，
        //            31                      是默认的质数
        int h = 0;
        h = h * 31 + firstName.hashCode();
        h = h * 31 + lastName.hashCode();
        h = h * 31 + age;
        return h;
        //                                                      简便方法
        //            return Objects.hash(firstName, lastName, age);
      }
    }
    List<Person> list =
        List.of(
            new Person("Xiao", "Ming", 18),
            new Person("Xiao", "Hong", 25),
            new Person("Bob", "Smith", 20));
    boolean exist = list.contains(new Person("Bob", "Smith", 20));
    System.out.println(exist ? "测试成功!" : "测试失败!");
  }

  /** map 的用法 --> hashMap，空间换时间， 查询速度有保证，存在空间浪费，key 乱序 */
  static void demo7() {
    Map<String, Integer> map = new HashMap<>();
    map.put("小明", 100);
    map.put("小红", 99);
    map.put("小李", 90);
    //                                                      注意看，顺序不一定是插入的顺序，
    for (String key : map.keySet()) {
      System.out.println(map.get(key));
    }
    System.out.println("----------");
    for (Map.Entry<String, Integer> entry : map.entrySet()) {
      System.out.println(entry.getKey());
      System.out.println(entry.getValue());
    }
  }

  /** enumMap --> hashMap */
  static void demo8() {
    //                                                      好处是不会有空间浪费，查询速度有保证
    enum WeekDay {
      Mon(1, "星期一"),
      Tue(2, "星期二");
      public final int value;
      public final String desc;

      private WeekDay(int value, String desc) {
        this.value = value;
        this.desc = desc;
      }
      ;
    }
    Map<WeekDay, String> tips = new EnumMap<WeekDay, String>(WeekDay.class /* 泛型擦拭 */);
    tips.put(WeekDay.Mon, "早点起床");
    tips.put(WeekDay.Tue, "吃好吃的");
  }

  /** sortMap --> treeMap 有序插入 必须实现Comparable 接口 */
  static void demo9() {
    class Person {
      String name;
      int age;

      public Person(String name, int age) {
        this.name = name;
        this.age = age;
      }
    }
    Person p1 = new Person("小红", 18);
    Person p2 = new Person("小明", 20);
    Person p3 = new Person("小李", 16);
    Map<Person, Integer> tree =
        new TreeMap<Person, Integer>(
            new Comparator<Person>() {
              @Override
              public int compare(Person o1, Person o2) {
                if (o1.age == o2.age) return 0;
                return o1.age > o2.age ? 1 : -1;
              }
            });
    tree.put(p1, 100);
    tree.put(p2, 90);
    tree.put(p3, 60);
    for (Person p : tree.keySet()) {
      System.out.println(p.name);
    }
  }

  /** set 用法 */
  static void demo10() {
    //                                                      无序
    Set<String> set = new HashSet<>();
    set.add("ann");
    set.add("anna");
    set.add("cungen");
    set.remove("ann");
    set.size();
    System.out.println(set.contains("anna"));
    System.out.println(Arrays.toString(set.toArray()));
    //                                                      有序
    Set<String> sortSet = new TreeSet<>();
    sortSet.add("cungen");
    sortSet.add("ningning");
    System.out.println(Arrays.toString(sortSet.toArray()));
  }

  /** 队列的用法 FIFO */
  //                    throw Exception	返回false或null
  // 添加元素到队尾	    add(E e)	    boolean offer(E e)
  // 取队首元素并删除	    E remove()	    E poll()
  // 取队首元素但不删除	E element()	    E peek()
  static void demo11() {
    // 这个栗子不报错
    Queue<String> queue = new LinkedList<>();

    System.out.println("早上 7：50");
    queue.offer("小明");
    queue.offer("小红");
    queue.offer("小胖");

    System.out.println("上午 8：00， 开始处理业务");
    System.out.println(queue.peek() + "满意的走了");
    queue.poll();
    System.out.println(queue.peek() + "满意的走了");
    queue.poll();
    System.out.println(queue.peek() + "满意的走了");
    queue.poll();
    System.out.print(queue.peek());
    System.out.println("队列空了， 但是我不报错");
    System.out.println(queue.size());
  }

  /** 会报错的队列 */
  static void demo12() {
    Queue<String> queue = new LinkedList<>();

    System.out.println("早上 7：50");
    queue.add("小明");
    queue.add("小红");
    queue.add("小胖");

    System.out.println("上午 8：00， 开始处理业务");
    System.out.println(queue.element() + "满意的走了");
    queue.remove();
    System.out.println(queue.element() + "满意的走了");
    queue.remove();
    System.out.println(queue.element() + "满意的走了");
    System.out.println("队列空了， 开始报错");
    queue.remove();
    System.out.print(queue.element());
    System.out.println(queue.size());
  }

  /** 优先队列 */
  static void demo13() {
    class User {
      String name;
      String number;

      public User(String name, String number) {
        this.name = name;
        this.number = number;
      }

      @Override
      public String toString() {
        return name + "/" + number;
      }
    }

    Queue<User> q =
        new PriorityQueue<>(
            new Comparator<User>() {
              @Override
              public int compare(User o1, User o2) {
                int n1 = Integer.parseInt(o1.number.substring(1));
                int n2 = Integer.parseInt(o2.number.substring(1));

                byte type1 = (byte) o1.number.charAt(0);
                byte type2 = (byte) o2.number.charAt(0);

                if (type1 == type2) {
                  return n1 > n2 ? 1 : -1;
                }

                return type1 > type2 ? -1 : 1;
              }
            });
    q.offer(new User("Bob", "A1"));
    q.offer(new User("Alice", "A2"));
    q.offer(new User("Boss", "V1"));
    q.offer(new User("Ann", "V2"));
    q.offer(new User("michael", "A3"));

    System.out.println(q.poll());
    System.out.println(q.poll());
    System.out.println(q.poll());
    System.out.println(q.poll());
    System.out.println(q.poll());
  }

  /** 双向队列 */
  static void demo14() {
    // 将元素添加到队尾或队首：addLast()/offerLast()/addFirst()/offerFirst()；
    // 从队首／队尾获取元素并删除：removeFirst()/pollFirst()/removeLast()/pollLast()；
    // 从队首／队尾获取元素但不删除：getFirst()/peekFirst()/getLast()/peekLast()；
    // 总是调用xxxFirst()/xxxLast()以便与Queue的方法区分开；
    // 避免把null添加到队列。
    Deque<String> q = new LinkedList<>();

    q.addLast("this");
    q.addLast("is");
    q.addLast("top");

    q.addFirst("oh");
    q.addFirst("my");
    q.addFirst("god");

    // 内部结构
    // god my oh this is top

    System.out.println(q.pollFirst());
    System.out.println(q.pollFirst());
    System.out.println(q.pollFirst());
    // System.out.println("first is done");
    // System.out.println(q.pollFirst());

    System.out.println(q.pollLast());
    System.out.println(q.pollLast());
    System.out.println(q.pollLast());

    // 两端压栈
  }

  /** 栈 */
  static void demo15() {
    Deque<String> stack = new LinkedList<>();

    // 入栈
    stack.push("ann");
    stack.push("michael");
    stack.push("guo");

    // 栈顶
    System.out.println(stack.peek());
    // 出栈
    System.out.println(stack.pop());
    System.out.println(stack.peek());
    System.out.println(stack.pop());
    System.out.println(stack.peek());
    System.out.println(stack.pop());
  }

  /**
   * iterator 迭代器
   *
   * <p>反向迭代器
   */
  static void demo16() {
    // forEach 的实现
    // for (Iterator<String> it = list.iterator(); it.hasNext(); ) {
    //   String s = it.next();
    //   System.out.println(s);
    // }

    class ReverseList<T> implements Iterable<T> {

      private List<T> list = new ArrayList<>();

      public void add(T t) {
        list.add(t);
      }

      @Override
      public Iterator<T> iterator() {
        return new ReverseIterator(list.size());
      }

      class ReverseIterator implements Iterator<T> {
        int index;

        ReverseIterator(int index) {
          this.index = index;
        }

        @Override
        public boolean hasNext() {
          return index > 0;
        }

        @Override
        public T next() {
          index--;
          return ReverseList.this.list.get(index);
        }
      }
    }

    ReverseList<String> rlist = new ReverseList<>();
    rlist.add("Apple");
    rlist.add("Orange");
    rlist.add("Pear");
    for (String s : rlist) {
      System.out.println(s);
    }
  }

  /** Collections 工具类 */
  // 创建空集合；
  // 创建单元素集合；
  // 创建不可变集合；
  // 排序／洗牌等操作。
  static void demo17() {
    List<String> list = new ArrayList<>();

    Collections.addAll(list, "cungen", "anna");
    Collections.addAll(list, new String[] {"guoguo", "mother"});
    Collections.sort(list); // 排序

    System.out.println(Arrays.toString(list.toArray()));

    Collections.shuffle(list); // 洗牌
    System.out.println(Arrays.toString(list.toArray()));

    // 创建空集合
    // 目的是防止返回 null， 有利于代码健壮性
    {
      List<String> emptyList = Collections.emptyList(); // List.of()
      Map<String, String> emptyMap = Collections.emptyMap(); // Map.of()
      Set<String> emptySet = Collections.emptySet(); // Set.of()
    }

    // 创建单元素集合, 用于返回元素，有利于代码健壮性
    {
      List<String> singleList = Collections.singletonList("ann");
      Map<String, String> singleMap = Collections.singletonMap("wife", "ann");
      Set<String> single = Collections.singleton("ann");
    }

    // 创建安全的不可变的数组（不可以add, remove）
    {
      List<String> mutable = new ArrayList<>();
      mutable.add("apple");
      mutable.add("pear");
      // 变为不可变集合:
      List<String> immutable = Collections.unmodifiableList(mutable);
      // 立刻扔掉mutable的引用:
      mutable = null; // 关键！！！！
      System.out.println(immutable);
    }
  }

  static int findMissingNumber1(int start, int end, List<Integer> list) {
    Map<Integer, Boolean> map = new HashMap<>();

    for (Integer n : list) {
      map.put(n, true);
    }

    for (int i = start; i <= end; i++) {
      if (map.get(i) == null) {
        return i;
      }
    }

    return -1;
  }

  static int findMissingNumber(int start, int end, List<Integer> list) {
    int i = 0, len = list.size();

    while (i < len - 1) {
      if (list.get(i + 1) - list.get(i) > 1) {
        return list.get(i) + 1;
      }
      i++;
    }

    return -1;
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
