package com.vaika.backend.controller;

import com.vaika.backend.dto.AuthentificationDTO;
import com.vaika.backend.entity.User;
import com.vaika.backend.service.JwtService;
import com.vaika.backend.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping
public class UserController {
    private AuthenticationManager authenticationManager;
    private UserService userService;
    private JwtService jwtService;
    @PostMapping("/inscription")
    public void inscription(@RequestBody User user){
        log.info("inscription");
        this.userService.inscription(user);
    }

    @PostMapping("/connexion")
    public Map<String,String> connexion(@RequestBody AuthentificationDTO authentificationDTO){
        final Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authentificationDTO.email(), authentificationDTO.password())
        );
        if (authenticate.isAuthenticated()){
            return this.jwtService.generate(authentificationDTO.email());
        }
        return null;
    }
}
