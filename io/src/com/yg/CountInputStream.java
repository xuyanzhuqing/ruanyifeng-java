package com.yg;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

// filter 模式，减少继承带来的类膨胀
// Java的IO标准库使用Filter模式为InputStream和OutputStream增加功能：
//
// 可以把一个InputStream和任意个FilterInputStream组合；
// 可以把一个OutputStream和任意个FilterOutputStream组合。
// Filter模式可以在运行期动态增加功能（又称Decorator模式）。
public class CountInputStream extends FilterInputStream {
  private int count = 0;

  CountInputStream(InputStream input) {
    super(input);
  }

  public int getCount() {
    return count;
  }

  @Override
  public int read() throws IOException {
    int n = in.read();

    if (n != -1) {
      this.count++;
    }

    return n;
  }

  @Override
  public int read(byte[] b, int off, int len) throws IOException {
    int n = in.read(b, off, len);

    if (n != -1) {
      this.count += n;
    }

    return n;
  }
}
