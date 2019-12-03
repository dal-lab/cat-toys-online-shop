package com.dallab.cattoys.controller;

import com.dallab.cattoys.application.UserService;
import com.dallab.cattoys.domain.User;
import com.dallab.cattoys.dto.UserDto;
import com.github.dozermapper.core.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private Mapper mapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void signup(
            @RequestBody UserDto userDto
    ) {
        User user = mapper.map(userDto, User.class);

        userService.register(user);
    }

}
