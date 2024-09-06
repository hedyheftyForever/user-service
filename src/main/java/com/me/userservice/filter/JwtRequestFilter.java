package com.me.userservice.filter;

import com.me.userservice.utils.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
public class JwtRequestFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (request.getRequestURI().contains("login")) {
            log.info("login, pass filter");
            filterChain.doFilter(request, response);
        }

        String authHeader = request.getHeader("Authorization");
        if (authHeader == null) {
            handleFailure(response, "cannot find auth header");
            return;
        }

        if (!authHeader.startsWith("Bearer ")) {
            handleFailure(response, "auth protocol not start with Bearer: " + authHeader);
            return;
        }

        String jwtToken = authHeader.substring(7);
        String username = JwtUtils.extractUsername(jwtToken);

        if (username == null) {
            handleFailure(response, "username is null");
            return;
        }

        if (!JwtUtils.validateToken(jwtToken, username)) {
            handleFailure(response, "auth failed");
            return;
        }

        filterChain.doFilter(request, response);
    }

    private void handleFailure(HttpServletResponse response, String errorMessage) throws IOException {
        log.error(errorMessage);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write(errorMessage);
    }
}
