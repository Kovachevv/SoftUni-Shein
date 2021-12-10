package com.example.shein.service.impl;

import com.example.shein.model.entity.BrandEntity;
import com.example.shein.model.entity.UserEntity;
import com.example.shein.model.entity.UserRoleEntity;
import com.example.shein.model.enums.UserRoleEnum;
import com.example.shein.model.service.BrandService.BrandAddServiceModel;
import com.example.shein.model.service.BrandService.BrandUpdateServiceModel;
import com.example.shein.model.view.BrandDetailsViewModel;
import com.example.shein.model.view.BrandViewModel;
import com.example.shein.repository.BrandRepository;
import com.example.shein.repository.UserRepository;
import com.example.shein.service.BrandService;
import javassist.tools.rmi.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.*;

@Service
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;


    public BrandServiceImpl(BrandRepository brandRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.brandRepository = brandRepository;
        this.userRepository = userRepository;
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
        return this.modelMapper
                .map(brandEntity, BrandViewModel.class);
    }


    @Override
    public BrandDetailsViewModel findById(Long id, String currentUser) {
        return brandRepository.
                findById(id).
                map(o -> mapDetailsView(currentUser, o))
                .get();
    }

    @Override
    public void addBrand(BrandAddServiceModel brandAddServiceModel) {
        BrandEntity brandEntity = modelMapper.map(brandAddServiceModel, BrandEntity.class);
        brandRepository.save(brandEntity);
    }

    @Override
    public void deleteBrand(Long id) {
        brandRepository.deleteById(id);
    }

    @Override
    public boolean isAdmin(String userName) {
        UserEntity user = userRepository.findByUsername(userName).orElse(null);
        if (user == null) {
            return false;
        }
        return user.
                getRoles().
                stream().
                map(UserRoleEntity::getRole).
                anyMatch(r -> r == UserRoleEnum.ADMIN);

    }

    private BrandDetailsViewModel mapDetailsView(String currentUser, BrandEntity brand) {
        BrandDetailsViewModel brandDetailsViewModel = this.modelMapper.map(brand, BrandDetailsViewModel.class);
        brandDetailsViewModel.setCanDelete(isAdmin(currentUser));
        brandDetailsViewModel.setDescription(brand.getDescription()).setImageUrl(brand.getImageUrl()).setName(brand.getName());
        return brandDetailsViewModel;
    }

    @Override
    public void initBrands() {
        if (brandRepository.count() == 0) {

            BrandEntity nike = new BrandEntity();
            nike.setName("Nike").setImageUrl("https://c.static-nike.com/a/images/w_1920,c_limit/bzl2wmsfh7kgdkufrrjq/image.jpg").
                    setDescription("Nike is an icon born under the name Blue Ribbon Sports in Oregon, USA, in 1972. Since its establishment until today, the brand has produced sneakers and apparel that push the limits every day. Nike Air Max, Huarache, Air Force or Roshe are just an example of what we offer.");
            BrandEntity adidas = new BrandEntity();
            adidas.setName("Adidas").setImageUrl("https://logos-world.net/wp-content/uploads/2020/04/Adidas-Symbol.png").
                    setDescription("The legacy, history and legend that created the whole culture around sneakers. adidas Originals has been dominating the field of men''s fashion, sneakers and sportswear for decades. Why? Because behind the unceasing progress of the Originals lies proven quality, reliable materials and innovation dressed in the best of the past. Read on to find out why adidas Originals sneakers and shoe wear are so popular today and how they managed to dominate almost all men''s footwear.");

            brandRepository.saveAll(List.of(nike,adidas));
        }
    }

    @Override
    public void updateBrand(BrandUpdateServiceModel serviceModel) throws ObjectNotFoundException {
        BrandEntity brandEntity = brandRepository.findById(serviceModel.getId()).orElseThrow(() ->
                new ObjectNotFoundException("Brand with id " + serviceModel.getId() + " not found!"));
        brandEntity.setName(serviceModel.getName()).
                setDescription(serviceModel.getDescription()).setImageUrl(serviceModel.getImageUrl());
        brandRepository.save(brandEntity);

    }

}
