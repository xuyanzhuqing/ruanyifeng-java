package com.itranswarp.sample;
// 必须引入java.xml模块后才能使用其中的类:
import javax.xml.XMLConstants;
public class Main {
    public static void main(String[] args) {
        var g = new Greeting();
        g.hello(XMLConstants.XML_NS_PREFIX);
    }
}
