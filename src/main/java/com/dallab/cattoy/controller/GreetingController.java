package com.dallab.cattoy.controller;

import com.dallab.cattoy.application.GreetingService;
import com.dallab.cattoy.dto.Greeting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    @Autowired
    private GreetingService greeingService;

    @GetMapping("/hello")
    public Greeting hello(
            @RequestParam(required=false) String name
    ) {
        Greeting greeting = new Greeting();
        greeting.setName("Ashal");
        greeting.setMessage(greeingService.getMessage(name));

        return greeting;
    }

}
