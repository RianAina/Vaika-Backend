package com.vaika.backend.service;

import com.vaika.backend.entity.Brand;
import com.vaika.backend.entity.Model;
import com.vaika.backend.repository.BrandRepository;
import com.vaika.backend.repository.ModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModelService {
    @Autowired
    private BrandRepository brandRepository;
    @Autowired
    private ModelRepository modelRepository;
    public void AddModel (Model model){

        if (model.getModelName() == null){
            throw new RuntimeException("Model name should not be null");
        }

        Brand brand = model.getBrand();
        if (brand == null) {
            throw new RuntimeException("Invalid brand for the model");
        }
        if (!brandRepository.existsById(brand.getIdBrand())){
            brandRepository.save(brand);
        }
        model.setBrand(brand);

        modelRepository.save(model);

    }
}
