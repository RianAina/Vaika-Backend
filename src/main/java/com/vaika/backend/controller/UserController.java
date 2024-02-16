package com.vaika.backend.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
@RestController
@RequestMapping
public class UserController {
    @PostMapping("inscription")
    public void inscription(){
        log.info("inscription");
    }
}
