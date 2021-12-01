package com.example.shein.service.impl;

import com.example.shein.model.entity.BrandEntity;
import com.example.shein.model.service.BrandAddServiceModel;
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
                map(this::map).
                collect(Collectors.toList());
    }

    private BrandViewModel map(BrandEntity brandEntity) {
        BrandViewModel brandViewModel = this.modelMapper
                .map(brandEntity, BrandViewModel.class);
        return brandViewModel;
    }



    @Override
    public BrandDetailsViewModel findById(Long id) {
        return brandRepository.
                findById(id).
                map(o -> mapDetailsView(o))
                .get();
    }

    @Override
    public void addBrand(BrandAddServiceModel brandAddServiceModel) {
        BrandEntity brandEntity = modelMapper.map(brandAddServiceModel, BrandEntity.class);
        brandRepository.save(brandEntity);
    }


    private BrandDetailsViewModel mapDetailsView( BrandEntity brand) {
        BrandDetailsViewModel brandDetailsViewModel = this.modelMapper.map(brand, BrandDetailsViewModel.class);
        brandDetailsViewModel.setDescription(brand.getDescription()).setImageUrl(brand.getImageUrl()).setName(brand.getName());
        return brandDetailsViewModel;
    }


}
