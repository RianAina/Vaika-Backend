package com.vaika.backend.controller;

import com.vaika.backend.entity.Brand;
import com.vaika.backend.entity.Car;
import com.vaika.backend.service.BrandService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@AllArgsConstructor
@RestController
@RequestMapping("/api/brand")
public class BrandController {
    private final BrandService brandService;

    @PostMapping("/create")
    public ResponseEntity<String> createBrand(@RequestBody Brand brand){
        try{
            brandService.AddBrand(brand);
            return new ResponseEntity<>("Brand created successfully", HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
