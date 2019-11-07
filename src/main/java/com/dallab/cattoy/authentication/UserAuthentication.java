package com.dallab.cattoy.authentication;

import io.jsonwebtoken.Claims;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

// 커스텀 인증 객체.
public class UserAuthentication extends AbstractAuthenticationToken {

    private Claims claims;

    public UserAuthentication(Claims claims) {
        super(authorities(claims));
        this.claims = claims;
    }

    private static List<GrantedAuthority> authorities(Claims claims) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("USER"));

        // FIXME: 사용자 ID가 1이면 관리자 권한 부여.
        Long userId = claims.get("userId", Long.class);
        if (userId == 1L) {
            authorities.add(new SimpleGrantedAuthority("ADMIN"));
        }

        return authorities;
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
