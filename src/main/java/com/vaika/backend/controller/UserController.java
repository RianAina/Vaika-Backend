package com.vaika.backend.controller;

import com.vaika.backend.entity.User;
import com.vaika.backend.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping
public class UserController {
    private UserService userService;
    @PostMapping("/inscription")
    public void inscription(@RequestBody User user){
        log.info("inscription");
        this.userService.inscription(user);
    }
}
