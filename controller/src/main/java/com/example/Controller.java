package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Controller {
    public static void main(String[] args) {
        SpringApplication.run(Controller.class, args);
        System.out.println("Controller Started..................");
    }
}