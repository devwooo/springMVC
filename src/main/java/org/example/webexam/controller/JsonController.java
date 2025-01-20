package org.example.webexam.controller;


import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JsonController {
    @GetMapping("/hello.json")
    public Hello hello() {
        Hello h = new Hello();
        h.setMessage("Hello World");
        h.setCount(10);
        return h;
    }
}

@Data
class Hello {
    private String message;
    private int count;
}
