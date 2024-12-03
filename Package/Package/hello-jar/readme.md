1. 打包程序
```bash
javac -d ./bin src/**/*.java
```
2. 将bin包内的文件复制到 hello-jar 中
3. 将hello-jar 文件压缩为zip 文件
4. 将 .zip 文件后缀改为 .jar
5. 运行jar 包
```bash
java -cp ./hello-jar com.itranswrap.world.Main 
```
