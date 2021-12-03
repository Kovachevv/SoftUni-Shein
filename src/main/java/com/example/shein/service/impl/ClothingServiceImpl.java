package com.example.shein.service.impl;

import com.example.shein.model.entity.BrandEntity;
import com.example.shein.model.entity.ClothingEntity;
import com.example.shein.model.view.ClothingDetailsView;
import com.example.shein.model.view.ClothingSummaryView;
import com.example.shein.repository.ClothingRepository;
import com.example.shein.service.ClothingService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClothingServiceImpl implements ClothingService {

    private final ClothingRepository clothingRepository;
    private final ModelMapper modelMapper;

    public ClothingServiceImpl(ClothingRepository clothingRepository, ModelMapper modelMapper) {
        this.clothingRepository = clothingRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public List<ClothingSummaryView> getAllClothes() {
       return clothingRepository.findAll().stream().map(clothingEntity -> {
           ClothingSummaryView clothingSummaryView = this.modelMapper.map(clothingEntity, ClothingSummaryView.class);
           clothingSummaryView.setBrand(clothingEntity.getBrand().getName());
           return clothingSummaryView;
        }).collect(Collectors.toList());
    }

    @Override
    public ClothingDetailsView findById(Long id) {
        return clothingRepository.findById(id).map(clothingEntity -> {
            ClothingDetailsView clothingDetailsView = this.modelMapper.map(clothingEntity,ClothingDetailsView.class);
            clothingDetailsView.setBrand(clothingEntity.getBrand().getName());
            return clothingDetailsView;
        }).get();
    }

}
