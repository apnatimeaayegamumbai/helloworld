package com.apnatimeaayega.sample.controllers;

import com.apnatimeaayega.sample.domain.Hello;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {

    @RequestMapping("/param")
    public Hello sayHelloUser(@RequestParam(value = "name", defaultValue = "World!")String name) {
        return new Hello(name, 10);
    }

    @RequestMapping("/")
    public String sayHello() {
        return "Hello!";
    }
}
