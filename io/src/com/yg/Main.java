package com.yg;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Serializable;
import java.io.Writer;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class Main {
  public static void main(String[] args) {
    autoRun(Main.class);
  }

  /* File 文件/文件夹对象 */
  static void demo1() throws IOException {
    File f = new File("./test.txt");
    System.out.println(f.getPath()); // 传入的路径
    System.out.println(f.getAbsolutePath()); // 绝对路径 /a/b/c/     -> /a/b/c/../
    System.out.println(f.getCanonicalPath()); // 规范绝对路径 /a/b/c -> a/b

    System.out.println(File.separator); // 不同平台的路径分隔符， windows \ mac /
    f.isFile(); // 是否是存在
    f.isDirectory(); // 是否是目录
    f.canExecute(); // 对于文件夹，是看能否访问子文件及子文件夹
    f.canRead(); // 可读
    f.canWrite(); // 可写
    f.length(); // 字节数

    {
      // 创建文件
      if (f.createNewFile()) {
        // 删除文件
        f.delete();
      }
    }

    // 创建临时文件
    {
      File f1 = File.createTempFile("tmp-", "txt");
      System.out.println(f1.getCanonicalPath());
      f1.deleteOnExit(); // 虚拟机关闭时删除该临时文件
    }

    // 列出文件夹
    {
      File f1 = new File("./io");
      System.out.println(Arrays.toString(f1.list())); // 文件/文件夹名
      for (File ff : f1.listFiles()) { // 列出子一级文件
        System.out.println(ff.getCanonicalPath());
      }
    }

    // 创建文件夹
    {
      File file = new File("./io/test/a/b/c");
      file.mkdirs(); // 创建多级目录, 会自动创建不存在的父目录 mkdir -p
      new File("./io/test/aa").mkdir(); // 创建目录 mkdir
    }
  }

  /** Path */
  static void demo2() {
    Path path = Paths.get(".", "io", "test"); // 拼接路径

    System.out.println(path);
    System.out.println(path.toAbsolutePath());
    System.out.println(path.normalize()); // 规范路径
    System.out.println(path.getParent());

    File f = path.toFile(); // 变为 File 对象
    System.out.println(f.isDirectory());

    for (Path p : Paths.get("..").toAbsolutePath()) { // 可以直接遍历Path
      System.out.println("  " + p);
    }
  }

  /**
   * FileInputStream 基础版
   *
   * <p>read 方法是阻塞的，无法预知时间
   */
  static void demo3() throws IOException {
    File file = Paths.get(".", "io", "test", "todolist.txt").toFile();
    if (!file.isFile()) {
      System.out.println("不是一个文件");
    }

    InputStream stream = new FileInputStream(file);
    for (; ; ) {
      int n = stream.read(); // 读取字节
      System.out.println((char) n);
      if (n == -1) { // 结束循环
        break;
      }
    }

    // 缺点报错之后，无法关闭流
    stream.close(); // 关闭流
  }

  /** FileInputStream 改进版 */
  static void demo4() throws IOException {
    File file = Paths.get(".", "io", "test", "todolist.txt").toFile();
    if (!file.isFile()) {
      System.out.println("不是一个文件");
    }

    InputStream stream = null;

    try {
      stream = new FileInputStream(file);
      int n;
      while ((n = stream.read()) != -1) { // 利用while同时读取并判断
        System.out.println((char) n);
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (stream != null) { // 保证流被关闭
        stream.close();
      }
    }
  }

  /** FileInputStream 终极版 */
  static void demo5() {
    File file = Paths.get(".", "io", "test", "todolist.txt").toFile();
    try (InputStream input = new FileInputStream(file)) {
      int n;

      while ((n = input.read()) != -1) {
        System.out.println((char) n);
      }
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /** FileInputStream 缓冲区 */
  static void demo6() throws IOException {
    File file = new File("./io/test/todolist.txt");
    try (InputStream input = new FileInputStream(file)) {
      int n;
      byte[] buffer = new byte[4];
      while ((n = input.read(buffer)) != -1) {
        System.out.println("read " + n + " bytes.");
      }
    }
  }

  /** ByteInputStream 字节流 */
  static void demo7() throws IOException {
    byte[] bytes = new byte[] {97, 97, 97, 98, 98, 98};
    try (InputStream input = new ByteArrayInputStream(bytes)) {
      int n;

      while ((n = input.read()) != -1) {
        System.out.println((char) n);
      }
    }
  }

  /** BufferedInputStream */
  static void demo8() throws IOException {
    byte[] bytes = new byte[] {97, 97, 97, 98, 98, 98};
    System.out.println(new String(bytes, 0, bytes.length)); // 打印 byte
    InputStream input = new ByteArrayInputStream(bytes);
    BufferedInputStream bs = new BufferedInputStream(input, 4);

    int n;
    while ((n = input.read()) != -1) {
      System.out.println((char) n);
    }

    bs.close(); // 关闭流 input & bs
  }

  /** FileOutputStream */
  static void demo9() throws IOException {
    Path path = Paths.get(".", "io", "test", "todolist.txt");
    File file = new File(path.toUri());

    System.out.println(file.isFile());

    try (OutputStream output = new FileOutputStream(file)) {
      output.write("hello".getBytes("UTF-8"));
      output.write("cungen".getBytes("UTF-8"));
    }
  }

  /** ByteArrayOutputStream */
  static void demo10() throws IOException {
    byte[] data;
    try (ByteArrayOutputStream output = new ByteArrayOutputStream()) {
      String[] poetry = new String[] {"李白乘舟将欲行", "忽闻岸上踏歌声", "桃花潭水深千尺", "不及汪伦送我情"};

      for (String line : poetry) {
        output.write(line.getBytes("UTF-8"));
      }
      data = output.toByteArray();
    }
    System.out.println(new String(data, "UTF-8"));
  }

  /** 复制诗歌到 */
  static void demo11() throws IOException {
    PipTo pip = new PipTo("./io/test/target.txt", "./io/test/source.txt");
    pip.run();
  }

  /** 序列化反序列化 */
  static void demo12() {
    class Person implements Serializable {
      private static final long serialVersionUID = 100L;

      private String name;
      private int age;

      public Person(String name, int age) {
        this.name = name;
        this.age = age;
      }

      public String getName() {
        return name;
      }

      public void setName(String name) {
        this.name = name;
      }

      public int getAge() {
        return age;
      }

      public void setAge(int age) {
        this.age = age;
      }

      @Override
      public String toString() {
        return name + ":" + age;
      }
    }

    // 序列化
    ByteArrayOutputStream buffer = new ByteArrayOutputStream();
    try (ObjectOutputStream os = new ObjectOutputStream(buffer)) {
      os.writeInt(12345);
      os.writeUTF("Hello");
      os.writeObject(Double.valueOf(123.456));
      os.writeObject(new Person("cungen", 18));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    System.out.println(Arrays.toString(buffer.toByteArray()));

    ByteArrayInputStream bufferin = new ByteArrayInputStream(buffer.toByteArray());
    // 反序列化,反序列化时，由JVM直接构造出Java对象，不调用构造方法，构造方法内部的代码，在反序列化时根本不可能执行
    try (ObjectInputStream in = new ObjectInputStream(bufferin)) {
      int n = in.readInt();
      String s = in.readUTF();
      Double d = (Double) (in.readObject());
      Person cungen = (Person) in.readObject();
      System.out.println(n);
      System.out.println(s);
      System.out.println(d);
      System.out.println(cungen);
    } catch (IOException | ClassNotFoundException e) {
      throw new RuntimeException(e);
    }
  }

  /** Reader FileReader CharArrayReader StringReader InputStreamReader */
  static void demo13() {
    // Reader reader = new CharArrayReader("Hello".toCharArray())
    // Reader reader = new StringReader("Hello"))
    try (Reader reader = new FileReader("./io/test/source.txt", StandardCharsets.UTF_8)) {
      char[] buffer = new char[1000];
      int n;
      while ((n = reader.read(buffer)) != -1) {
        System.out.println("read " + n + " chars.");
        System.out.println(new String(buffer, 0, n));
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    // reader & inputStream 的关系
    //     // 持有InputStream:
    //     InputStream input = new FileInputStream("src/readme.txt");
    // // 变换为Reader:
    //     Reader reader = new InputStreamReader(input, "UTF-8");
  }

  /** Writer FileWriter CharArrayWriter StringWriter OutputStreamWriter */
  static void demo14() {
    try (Writer writer = new FileWriter("./io/test/test.txt", StandardCharsets.UTF_8)) {
      writer.write('H'); // 写入单个字符
      writer.write("Hello".toCharArray()); // 写入char[]
      writer.write("Hello"); // 写入String
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /** 小文件读写 */
  static void demo15() throws IOException {
    {
      String path = "./io/test/source.txt";
      // 读取所有数据
      byte[] data = Files.readAllBytes(Path.of(path));

      // 默认使用UTF-8编码读取:
      String content1 = Files.readString(Path.of(path));
      // 可指定编码:
      String content2 = Files.readString(Path.of(path), StandardCharsets.ISO_8859_1);
      // 按行读取并返回每行内容:
      List<String> lines = Files.readAllLines(Path.of(path));
    }

    // 写入二进制文件:
    byte[] data = new byte[] {97};
    Files.write(Path.of("/path/to/file.txt"), data);
    // 写入文本并指定编码:
    Files.writeString(Path.of("/path/to/file.txt"), "文本内容...", StandardCharsets.ISO_8859_1);
    // 按行写入文本:
    List<String> lines = List.of(new String[] {});
    Files.write(Path.of("/path/to/file.txt"), lines);
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
      String name = "demo" + max;
      System.out.println(name + " is running");
      Method method = cls.getDeclaredMethod(name);
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
      String name = "demo" + which;
      System.out.println(name + " is running");
      Method method = cls.getDeclaredMethod(name);
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
