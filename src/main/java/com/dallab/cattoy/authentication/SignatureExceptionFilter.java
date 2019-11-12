package com.dallab.cattoy.authentication;

import io.jsonwebtoken.security.SignatureException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// JWT SignatureException 에러 처리 필터.
public class SignatureExceptionFilter extends HttpFilter {

    @Override
    public void doFilter(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain
    ) throws IOException, ServletException {
        try {
            chain.doFilter(request, response);
        } catch (SignatureException e) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Invalid Token");
        }
    }

}
