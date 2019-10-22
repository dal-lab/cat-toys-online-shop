package com.dallab.cattoy.application;

import com.dallab.cattoy.domain.Greeting;
import org.springframework.stereotype.Service;

@Service
public class GreetingService {

    public String getMessage(String name) {
        Greeting greeting = Greeting.builder()
                .name(name)
                .build();

        return greeting.getMessage();
    }

}
