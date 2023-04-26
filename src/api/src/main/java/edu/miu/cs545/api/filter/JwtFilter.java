package edu.miu.cs545.api.filter;

import edu.miu.cs545.api.util.JwtUtil;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.NonNull;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;
    public JwtFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {

        final String authorizationHeader = request.getHeader("Authorization");
        String name = null;
        String token = null;
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ") && !authorizationHeader.substring(7).isBlank()) {
            token = authorizationHeader.substring(7);
            try {
                name = jwtUtil.getUsernameFromToken(token);
            } catch (ExpiredJwtException e) { // TODO come back here!

            }
        }
        if (name != null && jwtUtil.validateToken(token)) {
            SecurityContextHolder.getContext().setAuthentication(jwtUtil.getAuthentication(token, request));
        }
        filterChain.doFilter(request, response);
    }
}
