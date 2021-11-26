package com.example.shein.service.impl;

import com.example.shein.model.entity.ClothingEntity;
import com.example.shein.repository.ClothingRepository;
import com.example.shein.service.ClothingService;
import org.springframework.stereotype.Service;

@Service
public class ClothingServiceImpl implements ClothingService {

    private final ClothingRepository clothingRepository;

    public ClothingServiceImpl(ClothingRepository clothingRepository) {
        this.clothingRepository = clothingRepository;
    }


    @Override
    public void initClothes() {

        ClothingEntity sweatshirt = new ClothingEntity();

        //TODO finish implementing after adding brands




    }
}
