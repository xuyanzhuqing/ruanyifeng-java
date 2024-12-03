模块
最大的好处： jre目录打个包给对方发过去，对方直接运行上述命令即可，既不用下载安装JDK，也不用知道如何配置我们自己的模块，极大地方便了分发和部署。

1. 编译代码到 bin 目录
```bash
javac -d bin src/module-info.java src/com/itranswarp/sample/*.java
```

2. 打 jar 包
```bash
 jar --create --file hello.jar --main-class com.itranswarp.sample.Main -C bin .
# 得到hello.jar, 运行命令
java -jar hello.jar
```

3. 使用JDK自带的jmod命令把一个jar包转换成模块
```bash
 jmod create --class-path hello.jar hello.jmod # 得到hello.jmod
 
# 运行模块
java --module-path hello.jar --module hello.world #注意这里不是直接运行jmod
```

4. 通过 jmod 打包 jre
```bash
jlink --module-path hello.jmod --add-modules java.base,java.xml,hello.world --output jre/

#运行jre
jre/bin/java --module hello.world 
```

