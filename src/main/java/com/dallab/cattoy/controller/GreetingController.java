package com.dallab.cattoy.controller;

import com.dallab.cattoy.dto.Greeting;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    @GetMapping("/hello")
    public Greeting hello(
            @RequestParam(defaultValue="world") String name
    ) {
        Greeting greeting = new Greeting();
        greeting.setName("Ashal");
        greeting.setMessage("Hello, " + name);

        return greeting;
    }

}
