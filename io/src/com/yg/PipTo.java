package com.yg;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Paths;

/** 复制一个文件到另一个文件 */
public class PipTo {
  private File target;
  private File source;

  public PipTo(String target, String source) {
    this.target = new File(Paths.get(target).toUri());
    this.source = new File(Paths.get(source).toUri());
  }

  public PipTo(File target, File source) {
    this.target = target;
    this.source = source;
  }

  public void run() throws IOException {
    System.out.println(source.getAbsolutePath());
    System.out.println(target.getAbsolutePath());
    System.out.println("start copy");
    try (InputStream input = new FileInputStream(source);
        OutputStream output = new FileOutputStream(target)) {
      int n;
      byte[] buffer = new byte[4];
      while ((n = input.read(buffer)) != -1) {
        output.write(buffer);
      }
      output.flush();
    }
    System.out.println("copy end");
  }
}
