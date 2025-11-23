package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/")
    public String home() {
        return "Hello from Java Spring Boot App!";
    }

    @GetMapping("/status")
    public String status() {
        return "Application is running successfully 🚀";
    }
}
