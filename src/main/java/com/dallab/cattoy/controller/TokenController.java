package com.dallab.cattoy.controller;

import com.dallab.cattoy.application.UserService;
import com.dallab.cattoy.domain.User;
import com.dallab.cattoy.dto.SigninRequestDto;
import com.dallab.cattoy.dto.SigninResponseDto;
import com.dallab.cattoy.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/token")
public class TokenController {

    @Autowired
    private UserService userService;

    @Autowired
    JwtUtil jwtUtil;

    @PostMapping
    public ResponseEntity<?> signin(
            @Valid @RequestBody SigninRequestDto signinDto
    ) throws URISyntaxException {
        String email = signinDto.getEmail();
        String password = signinDto.getPassword();

        User user = userService.authenticate(email, password);
        if (user == null) {
            throw new EntityNotFoundException();
        }

        String token = jwtUtil.createToken(user.getId(), user.getName());

        return ResponseEntity.created(new URI("/"))
                .body(new SigninResponseDto(token));
    }

}
