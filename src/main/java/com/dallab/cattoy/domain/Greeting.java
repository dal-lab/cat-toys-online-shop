package com.dallab.cattoy.domain;

import lombok.Builder;

@Builder
public class Greeting {

    private String name;

    public String getMessage() {
        if (name == null) {
            return "Hello";
        }
        return "Hello, " + name;
    }

}
