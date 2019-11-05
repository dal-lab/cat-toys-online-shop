package com.dallab.cattoy.controller;

import com.dallab.cattoy.application.UserService;
import com.dallab.cattoy.domain.User;
import com.dallab.cattoy.dto.UserDto;
import com.github.dozermapper.core.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private Mapper mapper;

    @PostMapping
    public ResponseEntity<?> signup(
            @RequestBody UserDto userDto
    ) throws URISyntaxException {
        User user = mapper.map(userDto, User.class);

        userService.register(user);

        URI location = new URI("/");

        return ResponseEntity.created(location).build();
    }

}
