package com.vaika.backend.service;

import com.vaika.backend.entity.Brand;
import com.vaika.backend.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BrandService {
    @Autowired
    private BrandRepository brandRepository;
    public void AddBrand (Brand brand){

        if (brand.getBrandName() == null){
            throw new RuntimeException("Brand name should not be null");
        }
        brandRepository.save(brand);
    }
}
