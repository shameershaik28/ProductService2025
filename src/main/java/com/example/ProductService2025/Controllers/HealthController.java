package com.example.ProductService2025.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    @GetMapping("/")
    public String home() {
        return "App is healthy and running!";
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello from AWS!";
    }

    @GetMapping("/health")
    public String healthCheck() {
        return "Healthy ✅";
    }
}
