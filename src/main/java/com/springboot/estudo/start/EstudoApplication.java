package com.springboot.estudo.start;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@ComponentScan(basePackages = "com.springboot.estudo")
public class EstudoApplication {

    public static void main(String[] args) {
        SpringApplication.run(EstudoApplication.class, args);
    }
}
