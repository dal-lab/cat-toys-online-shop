package com.dallab.cattoys.controller;

import com.dallab.cattoys.application.UserService;
import com.dallab.cattoys.domain.User;
import com.dallab.cattoys.dto.SigninRequestDto;
import com.dallab.cattoys.dto.SigninResponseDto;
import com.dallab.cattoys.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/token")
public class TokenController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SigninResponseDto signin(
            @Valid @RequestBody SigninRequestDto signinDto
    ) throws URISyntaxException {
        String email = signinDto.getEmail();
        String password = signinDto.getPassword();

        User user = userService.authenticate(email, password);
        if (user == null) {
            throw new EntityNotFoundException();
        }

        String token = jwtUtil.createToken(user.getId(), user.getName());

        return new SigninResponseDto(token);
    }

}
