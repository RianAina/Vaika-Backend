package com.vaika.backend.controller;

import com.vaika.backend.entity.Brand;
import com.vaika.backend.entity.Car;
import com.vaika.backend.service.BrandService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/brand")
public class BrandController {
    private final BrandService brandService;
    @GetMapping("/getAll")
    public ResponseEntity<List<Brand>> getAllBrands() {
        try {
            List<Brand> brands = brandService.getAllBrands();
            return new ResponseEntity<>(brands, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

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
