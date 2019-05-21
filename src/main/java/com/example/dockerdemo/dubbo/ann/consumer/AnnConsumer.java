package com.example.dockerdemo.dubbo.ann.consumer;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AnnConsumer {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConsumerConfiguration.class);
        context.start();
        AnnotationAction annotationAction = (AnnotationAction) context.getBean("annotationAction");
        String sayHello = annotationAction.doSayHello("World");
        System.out.println("Received: " + sayHello);
    }
}
