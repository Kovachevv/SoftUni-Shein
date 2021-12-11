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

            BrandEntity converse = new BrandEntity();
            converse.setName("Converse").setImageUrl("https://i.pinimg.com/originals/82/47/37/82473738c0e36e463be9de989ace02df.jpg").
                    setDescription("Converse trainers, Converse sneakers, Converse canvas shoes, or simply Converse - call them however you want, they are the one and only! What is important is that this brand came on the market as early as 1908 as the Converse Rubber Shoe Company, which ranks Converse shoes as sneakers with the longest and, possibly, the richest history.");
            BrandEntity jordan = new BrandEntity();

            jordan.setName("Jordan").setImageUrl("https://i.pinimg.com/222x/a2/bc/bb/a2bcbb216b790eb34844962944a3a16e.jpg").
                    setDescription("A wide selection of unique shoes and apparel that bear the surname of the most famous basketball player of all time. Michael Jordan joined Nike nearly thirty years ago and Nike has already released dozens of models in hundreds of colors under his auspice.");
            BrandEntity vans = new BrandEntity();

            vans.setName("Vans").setImageUrl("https://wallpaperaccess.com/full/889590.jpg").
                    setDescription("Van Doren Rubber Co. was established in 1966 by Paul Van Doren in Anaheim, California when he tried to mold soles on a classic waffle maker. For many people, it might have been a bizarre idea at that time but from today''s perspective, it has led to one of the greatest milestones in the production of modern footwear.");

            BrandEntity puma = new BrandEntity();

            puma.setName("Puma").setImageUrl("https://upload.wikimedia.org/wikipedia/en/thumb/4/49/Puma_AG.svg/1920px-Puma_AG.svg.png").
                    setDescription("When you launch the adidas brand with your brother, but then you have a fall out, try creating the Puma brand. That is exactly how the German brand with the big cat in the emblem was born. Today it is one of the world''s largest manufacturers of clothing and footwear, offering everything from strictly sporty gadgets to purely lifestyle sneakers and clothing. In addition, Puma likes to collaborate with well-known artists so you can add to your shoe collection or wardrobe a piece that has been designed in the hands of singer the Weeknd and Barbarian singer Rihanna.");
            BrandEntity reebok = new BrandEntity();

            reebok.setName("Reebok").setImageUrl("https://logos-download.com/wp-content/uploads/2016/02/Reebok_Logo_2019.png").
                    setDescription("Reebok was founded in 1895 in England.It's a dynamically developing brand. It supports a person''s individuality not only in sports but also in everyday life.The goal of the brand is to offer shoes that fit customers perfectly, are comfortable and cater to their needs. The guarantee of customer satisfaction is modern technology, high quality materials, original colorways and increasingly honed cuts. It''s important for the brand to enjoy your sporting experience and have fun during it.");

            BrandEntity theNorthFace = new BrandEntity();

            theNorthFace.setName("The North Face").setImageUrl("https://logos-world.net/wp-content/uploads/2020/11/The-North-Face-Logo.png").
                    setDescription("The North Face is an American brand dedicated to producing outdoor equipment in extreme conditions. This brand was founded in California in 1968 and today we can buy clothing and footwear not only for sports and hiking, but also for casual wear.");
            brandRepository.saveAll(List.of(nike, adidas,converse,jordan,vans,puma,reebok,theNorthFace));

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
