package com.dallab.cattoys.util;

import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class JwtUtilTest {

    private static final String SECRET = "12345678901234567890123456789012";

    private static final String TOKEN = "eyJhbGciOiJIUzI1NiJ9." +
            "eyJ1c2VySWQiOjEzLCJuYW1lIjoi7YWM7Iqk7YSwIn0." +
            "yI3hxmFPMg4tbbxsUh11AzwfgbfxW_jrUaqFuzPTS64";

    private JwtUtil jwtUtil;

    @BeforeEach
    public void setUp() {
        jwtUtil = new JwtUtil(SECRET);
    }

    @Test
    public void createToken() {
        String token = jwtUtil.createToken(13L, "테스터");

        assertThat(token).isNotBlank();
    }

    @Test
    public void parseToken() {
        Claims claims = jwtUtil.parseToken(TOKEN);

        assertThat(claims.get("userId", Long.class)).isEqualTo(13L);
        assertThat(claims.get("name", String.class)).isEqualTo("테스터");
    }

}