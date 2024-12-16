### 编译
```bash
javac -d ./bin -cp ./lib/commons-logging-1.2.jar ./src/com/itranswarp/learnjava/Main.java
```
### 执行
```bash
java -cp .:./lib/commons-logging-1.2.jar:./bin com.itranswarp.learnjava.Main
```

### 编译 log4f2 与 commons log 代码写法一致，仅需在编译时引入即可
```bash
javac -d ./bin -cp ./lib/commons-logging-1.2.jar:./lib/log4j-api-2.11.2.jar:./lib/log4j-core-2.11.2.jar:./lib/log4j-jcl-2.11.2.jar ./src/com/itranswarp/learnjava/Main.java
```
### 执行 log4f2
```bash
java -cp .:./lib/commons-logging-1.2.jar:./lib/log4j-api-2.11.2.jar:./lib/log4j-core-2.11.2.jar:./lib/log4j-jcl-2.11.2.jar:./bin:./src com.itranswarp.learnjava.Main

# ./bin 用于寻找编译后的文件
# ./src 用于寻找log4j2.xml
```

slf4j
```bash
 javac -d ./bin -cp ./lib/slf4j-api-1.7.26.jar ./src/com/itranswarp/learnjava/Slf4j.java
```

```bash

```