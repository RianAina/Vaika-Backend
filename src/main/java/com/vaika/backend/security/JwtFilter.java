package com.vaika.backend.security;

import com.vaika.backend.entity.User;
import com.vaika.backend.service.JwtService;
import com.vaika.backend.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Service
public class JwtFilter extends OncePerRequestFilter {
    private UserService userService;
    private JwtService jwtService;

    public JwtFilter(UserService userService,JwtService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = null;
        String email = null;
        boolean isTokenExpired = true;
        //'Authorization: Basic dXNlcjozNDJjZTkzOS0zMmM4LTRkNTMtOTgxYy0xYWUyOGMwMzNmMTY='
        final String authorization = request.getHeader("Authorization");
        if (authorization != null && authorization.startsWith("Basic ")){
            token = authorization.substring(6);
            isTokenExpired = jwtService.isTokenExpired(token);
            jwtService.extractUsername(token);
        }
        if (!isTokenExpired && email != null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails = (User) userService.loadUserByUsername(email);
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }

        filterChain.doFilter(request,response);
    }
}
