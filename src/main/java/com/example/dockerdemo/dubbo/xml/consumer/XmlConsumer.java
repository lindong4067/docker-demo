package com.example.dockerdemo.dubbo.xml.consumer;

import com.example.dockerdemo.dubbo.inf.DemoService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class XmlConsumer {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("dubbo/xml/dubbo-consumer.xml");
        context.start();
        DemoService demoService = context.getBean("demoService", DemoService.class);
        String hello = demoService.sayHello("world");
        System.out.println("result: " + hello);
    }
}
