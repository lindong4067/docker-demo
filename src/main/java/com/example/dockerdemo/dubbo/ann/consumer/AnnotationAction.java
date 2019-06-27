package com.example.dockerdemo.dubbo.ann.consumer;

import com.example.dockerdemo.dubbo.inf.DemoService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Component;

//@Component("annotationAction")
public class AnnotationAction {
//    @Reference(interfaceClass = HelloService.class, version = AnnotationConstants.VERSION)
//    private HelloService helloService;
//
//    @Reference(interfaceClass = GreetingService.class,
//            version = AnnotationConstants.VERSION,
//methods = {@Method(name = "greeting", timeout = 250, retries = 1)})
//    @Reference
    private DemoService demoService;

    public String doSayHello(String name) {
        try {
            return demoService.sayHello(name);
        } catch (Exception e) {
            e.printStackTrace();
            return "Throw Exception";
        }
    }
}
