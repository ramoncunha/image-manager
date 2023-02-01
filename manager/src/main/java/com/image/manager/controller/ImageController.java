package com.image.manager.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ImageController {
    
    @GetMapping("/test")
    public String testGet() {
        return "Hello World!";
    }
}
