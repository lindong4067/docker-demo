package com.example.dockerdemo.dubbo.ann.consumer;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@EnableDubbo(scanBasePackages = "com.example.dockerdemo.dubbo.ann.consumer")
@PropertySource("classpath:/dubbo/ann/dubbo-consumer.properties")
@ComponentScan(basePackages = "com.example.dockerdemo.dubbo.ann.consumer")
public class ConsumerConfiguration {

}
