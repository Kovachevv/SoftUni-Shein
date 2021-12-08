package com.example.shein.service.impl;

import com.example.shein.model.entity.BrandEntity;
import com.example.shein.model.entity.ClothingEntity;
import com.example.shein.model.entity.UserEntity;
import com.example.shein.model.entity.UserRoleEntity;
import com.example.shein.model.enums.UserRoleEnum;
import com.example.shein.model.service.ClothesService.ClothesAddServiceModel;
import com.example.shein.model.service.ClothesService.ClothesUpdateServiceModel;
import com.example.shein.model.view.ClothingDetailsView;
import com.example.shein.model.view.ClothingSummaryView;
import com.example.shein.repository.BrandRepository;
import com.example.shein.repository.ClothingRepository;
import com.example.shein.repository.UserRepository;
import com.example.shein.service.ClothingService;
import javassist.tools.rmi.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClothingServiceImpl implements ClothingService {

    private final ClothingRepository clothingRepository;
    private final ModelMapper modelMapper;
    private final BrandRepository brandRepository;
    private final UserRepository userRepository;

    public ClothingServiceImpl(ClothingRepository clothingRepository, ModelMapper modelMapper, BrandRepository brandRepository, UserRepository userRepository) {
        this.clothingRepository = clothingRepository;
        this.modelMapper = modelMapper;
        this.brandRepository = brandRepository;
        this.userRepository = userRepository;
    }


    @Override
    public List<ClothingSummaryView> getAllClothes() {
        return clothingRepository.findAll().stream().map(clothingEntity -> {
            ClothingSummaryView clothingSummaryView = this.modelMapper.map(clothingEntity, ClothingSummaryView.class);
            clothingSummaryView.setBrand(clothingEntity.getBrand().getName());
            return clothingSummaryView;
        }).collect(Collectors.toList());
    }

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

    @Override
    public ClothingDetailsView findById(Long id,String username) {
        return clothingRepository.findById(id).map(clothingEntity -> {
            ClothingDetailsView clothingDetailsView = this.modelMapper.map(clothingEntity, ClothingDetailsView.class);
            clothingDetailsView.setBrand(clothingEntity.getBrand().getName()).setCanDelete(isAdmin(username));
            return clothingDetailsView;
        }).get();
    }

    @Override
    public void initClothes() {
        if (clothingRepository.count() == 0) {


            BrandEntity nike = brandRepository.findByName("Nike").orElseThrow(IllegalArgumentException::new);
            BrandEntity adidas = brandRepository.findByName("Adidas").orElseThrow(IllegalArgumentException::new);
         /*   BrandEntity converse = brandRepository.findByName("Converse").orElseThrow(IllegalArgumentException::new);
            BrandEntity jordan = brandRepository.findByName("Jordan").orElseThrow(IllegalArgumentException::new);
            BrandEntity vans = brandRepository.findByName("Vans").orElseThrow(IllegalArgumentException::new);
            BrandEntity puma = brandRepository.findByName("Puma").orElseThrow(IllegalArgumentException::new);
            BrandEntity reebok = brandRepository.findByName("Reebok").orElseThrow(IllegalArgumentException::new);
            BrandEntity theNorthFace = brandRepository.findByName("The North Face").orElseThrow(IllegalArgumentException::new);*/

            ClothingEntity clothingEntity = new ClothingEntity();
            clothingEntity.setName("Club Tee").setPrice(BigDecimal.valueOf(18.95)).setImageUrl("https://static.footshop.com/445450/37485.jpg").
                    setBrand(nike).setDescription("100% Cotton - A very soft and pleasant material whose properties include tensile strength and elongation at break. Unlike other materials, it does not get damaged easily when washed often.");

            ClothingEntity clothingEntity1 = new ClothingEntity();
            clothingEntity1.setName("Fleece Pullover Hoodie").setPrice(BigDecimal.valueOf(49.95)).setImageUrl("https://static.footshop.com/411985/85273.jpg").
                    setBrand(nike).setDescription("80% Cotton - A very soft and pleasant material whose properties include tensile strength and elongation at break. Unlike other materials, it does not get damaged easily when washed often.");

            ClothingEntity clothingEntity2 = new ClothingEntity();
            clothingEntity2.setName("Joggers BB").setPrice(BigDecimal.valueOf(39.95)).setImageUrl("https://static.footshop.com/602353/115462.jpg").
                    setBrand(nike).setDescription("80% Cotton - A very soft and pleasant material whose properties include tensile strength and elongation at break. Unlike other materials, it does not get damaged easily when washed often.\n" +
                    "        //20% Polyester - a very strong synthetic fiber that boasts high heat resistance and excellent odor absorption");

            clothingRepository.saveAll(List.of(clothingEntity, clothingEntity1, clothingEntity2));
        }
    }

    @Override
    public void addClothing(ClothesAddServiceModel serviceModel) {
        ClothingEntity clothingEntity = modelMapper.map(serviceModel, ClothingEntity.class);
        clothingRepository.save(clothingEntity);
    }

    @Override
    public void deleteClothing(Long id) {
        clothingRepository.deleteById(id);
    }

    @Override
    public void updateClothing(ClothesUpdateServiceModel serviceModel) throws ObjectNotFoundException {

        ClothingEntity clothingEntity = clothingRepository.findById(serviceModel.getId()).orElseThrow(() ->
                new ObjectNotFoundException("Clothing with id " + serviceModel.getId() + " not found!"));

        clothingEntity.setName(serviceModel.getName()).setPrice(serviceModel.getPrice()).setImageUrl(serviceModel.getImageUrl()).
                setDescription(serviceModel.getDescription());
            clothingRepository.save(clothingEntity);
//
//        BrandEntity brandEntity = brandRepository.findById(serviceModel.getId()).orElseThrow(() ->
//                new ObjectNotFoundException("Offer with id " + serviceModel.getId() + " not found!"));
//        brandEntity.setName(serviceModel.getName()).
//                setDescription(serviceModel.getDescription()).setImageUrl(serviceModel.getImageUrl());
//        brandRepository.save(brandEntity);

    }

}
