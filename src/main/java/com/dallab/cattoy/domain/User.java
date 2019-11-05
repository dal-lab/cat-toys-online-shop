package com.dallab.cattoy.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private String name;

    private String email;

    private String password;

}
