package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    private final GreetingService svc;

    // Constructor injection
    public GreetingController(GreetingService svc) {
        this.svc = svc;
    }

    @GetMapping("/greet")
    public String greet(@RequestParam(required = false) String name) {
        return svc.greet(name);
    }
}