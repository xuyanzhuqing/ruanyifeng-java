module hello.world {
    exports com.itranswarp.sample; // 导出包内的类
    requires java.base; // 可不写，会自动引入
    requires  java.xml;
}