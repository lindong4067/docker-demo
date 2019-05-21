package com.example.dockerdemo.dubbo.xml.provider;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class XmlProvider {
    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("dubbo/xml/dubbo-provider.xml");
        context.start();
        System.out.println("Xml Provider ready.");
        System.in.read();
    }
}
