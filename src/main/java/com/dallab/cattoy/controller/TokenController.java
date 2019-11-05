package com.dallab.cattoy.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/token")
public class TokenController {

    @PostMapping
    public ResponseEntity<?> signin() throws URISyntaxException {
        return ResponseEntity.created(new URI("/")).build();
    }

}
