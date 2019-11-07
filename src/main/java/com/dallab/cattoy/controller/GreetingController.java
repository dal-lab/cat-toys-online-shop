package com.dallab.cattoy.controller;

import com.dallab.cattoy.application.GreetingService;
import com.dallab.cattoy.dto.GreetingDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    @Autowired
    private GreetingService greeingService;

    @GetMapping("/hello")
    public GreetingDto hello(
            Authentication authentication,
            @RequestParam(required = false) String name
    ) {
        String myName = "Ashal";

        // 인증 객체가 있다면 이름을 바꿔줍니다.
        if (authentication != null) {
            myName = authentication.getName();
        }

        GreetingDto greetingDto = new GreetingDto();
        greetingDto.setName(myName);
        greetingDto.setMessage(greeingService.getMessage(name));

        return greetingDto;
    }

}
