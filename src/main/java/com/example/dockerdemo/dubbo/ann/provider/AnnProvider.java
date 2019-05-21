package com.example.dockerdemo.dubbo.ann.provider;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AnnProvider {
    public static void main(String[] args) throws Exception {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ProviderConfiguration.class);
        context.start();
        System.out.println("Xml Provider ready.");
        System.in.read();
    }
}
