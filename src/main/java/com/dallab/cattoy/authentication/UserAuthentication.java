package com.dallab.cattoy.authentication;

import io.jsonwebtoken.Claims;
import org.springframework.security.authentication.AbstractAuthenticationToken;

// 커스텀 인증 객체.
public class UserAuthentication extends AbstractAuthenticationToken {

    private Claims claims;

    public UserAuthentication(Claims claims) {
        super(null);
        this.claims = claims;
    }

    @Override
    public Object getPrincipal() {
        // 인증된 사용자 정보.
        // 이게 null이면 Contoller로 전달되지 않습니다.
        return claims;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public String getName() {
        // 인증된 사용자의 이름을 얻습니다.
        return claims.get("name", String.class);
    }

    @Override
    public boolean isAuthenticated() {
        return true;
    }

}
