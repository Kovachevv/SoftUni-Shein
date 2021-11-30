package com.example.shein.service.impl;

import com.example.shein.model.entity.BrandEntity;
import com.example.shein.model.view.BrandDetailsViewModel;
import com.example.shein.model.view.BrandViewModel;
import com.example.shein.repository.BrandRepository;
import com.example.shein.service.BrandService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.*;

@Service
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;
    private final ModelMapper modelMapper;


    public BrandServiceImpl(BrandRepository brandRepository, ModelMapper modelMapper) {
        this.brandRepository = brandRepository;

        this.modelMapper = modelMapper;
    }

    @Override
    public List<BrandViewModel> getAllBrands() {
        return brandRepository.findAll().
                stream().
                map(brandEntity -> {
                    return new BrandViewModel().setName(brandEntity.getName()).setDescription(brandEntity.getDescription()).setImageUrl(brandEntity.getImageUrl());
                }).collect(Collectors.toList());
    }


    @Override
    public BrandViewModel findById(Long id, String user) {

        return null;
    }


}
