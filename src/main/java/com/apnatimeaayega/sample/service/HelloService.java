package com.apnatimeaayega.sample.service;

import com.apnatimeaayega.sample.domain.Hello;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class HelloService {

    public String sayHello() {
        log.info("sayHello invoked");
        return "Hello!";
    }

    public Hello sayHelloUser(String name) {
        log.info(("sayHelloUser invoked"));
        return new Hello(name, 10);
    }
}
