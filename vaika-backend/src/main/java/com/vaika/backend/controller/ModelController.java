package com.vaika.backend.controller;

import com.vaika.backend.entity.Model;
import com.vaika.backend.service.ModelService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/model")
public class ModelController {
    private final ModelService modelService;
    @PostMapping("/create")
    public ResponseEntity<String> createModel(@RequestBody Model model) {
        try {
            modelService.AddModel(model);
            return new ResponseEntity<>("Model created successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
