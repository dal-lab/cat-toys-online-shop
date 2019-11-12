package com.dallab.cattoys.controller;

import com.dallab.cattoys.dto.GreetingDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    @GetMapping("/hello")
    public GreetingDto hello() {
        GreetingDto greetingDto = new GreetingDto();

        greetingDto.setName("Ashal");
        greetingDto.setMessage("Hello, world!");

        return greetingDto;
    }

}
