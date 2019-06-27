package com.example.dockerdemo.reactor.flux;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class DemoController {

//    public Mono<Foobar> foobar() {
//        return Mono.just(new Foobar());
//    }

    private class Foobar {
    }
}
