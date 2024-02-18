package com.vaika.backend.controller;

import com.vaika.backend.entity.Model;
import com.vaika.backend.service.ModelService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/model")
public class ModelController {
    private final ModelService modelService;
    @GetMapping("/getAll")
    public ResponseEntity<List<Model>> getAllModels() {
        try {
            List<Model> models = modelService.getAllModels();
            return new ResponseEntity<>(models, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
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
