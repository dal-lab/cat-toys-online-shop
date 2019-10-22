package com.dallab.cattoy.controller;

import com.dallab.cattoy.application.GreetingService;
import com.dallab.cattoy.dto.GreetingDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    @Autowired
    private GreetingService greeingService;

    @GetMapping("/hello")
    public GreetingDto hello(
            @RequestParam(required = false) String name
    ) {
        GreetingDto greetingDto = new GreetingDto();
        greetingDto.setName("Ashal");
        greetingDto.setMessage(greeingService.getMessage(name));

        return greetingDto;
    }

}
