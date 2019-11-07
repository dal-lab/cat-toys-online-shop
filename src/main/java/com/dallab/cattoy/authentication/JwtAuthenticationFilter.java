package com.dallab.cattoy.authentication;

import com.dallab.cattoy.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationFilter
        extends BasicAuthenticationFilter {

    private JwtUtil jwtUtil;

    public JwtAuthenticationFilter(
            AuthenticationManager authenticationManager,
            JwtUtil jwtUtil
    ) {
        super(authenticationManager);
        this.jwtUtil = jwtUtil;
    }

    // 요청을 처리하기 전에 이 필터가 실행됩니다.
    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain
    ) throws IOException, ServletException {
        // 인증 처리를 다른 메서드로 분리했습니다.
        Authentication authentication = getAuthentication(request);

        // 만약 인증에 성공했다면...
        if (authentication != null) {
            // 보안 컨텍스트에 인증 객체를 넣어줍니다.
            // 이렇게 하면 Controller에서 Authentication 객체를 쓸 수 있습니다.
            SecurityContext context = SecurityContextHolder.getContext();
            context.setAuthentication(authentication);
        }

        // 다음 필터를 실행합니다.
        chain.doFilter(request, response);
    }

    private Authentication getAuthentication(HttpServletRequest request) {
        // 헤더에 토큰 정보가 있는지 확인합니다.
        String authorization = request.getHeader("Authorization");
        if (authorization == null) {
            return null;
        }

        // 토큰을 추출하고, 데이터를 얻습니다.
        String token = authorization.substring("Bearer ".length());
        Claims claims = jwtUtil.parseToken(token);

        // 인증 객체를 만듭니다.
        Authentication authentication = new UserAuthentication(claims);

        return authentication;
    }

}
