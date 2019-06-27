package com.apnatimeaayega.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    private static Class<Application> application = Application.class;

    public static void main(String[] args) {
        SpringApplication.run(application, args);
    }
}
