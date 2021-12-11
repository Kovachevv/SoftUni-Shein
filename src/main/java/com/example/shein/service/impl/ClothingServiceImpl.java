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
            BrandEntity converse = brandRepository.findByName("Converse").orElseThrow(IllegalArgumentException::new);
            BrandEntity jordan = brandRepository.findByName("Jordan").orElseThrow(IllegalArgumentException::new);
            BrandEntity vans = brandRepository.findByName("Vans").orElseThrow(IllegalArgumentException::new);
            BrandEntity puma = brandRepository.findByName("Puma").orElseThrow(IllegalArgumentException::new);
            BrandEntity reebok = brandRepository.findByName("Reebok").orElseThrow(IllegalArgumentException::new);
            BrandEntity theNorthFace = brandRepository.findByName("The North Face").orElseThrow(IllegalArgumentException::new);

            ClothingEntity clubTee = new ClothingEntity();
            clubTee.setName("Club Tee").setImageUrl("https://static.footshop.com/445450/37485.jpg").
                    setBrand(nike).setDescription("100% Cotton - A very soft and pleasant material whose properties include tensile strength and elongation at break. Unlike other materials, it does not get damaged easily when washed often.");

            ClothingEntity pullOverHoodie = new ClothingEntity();
            pullOverHoodie.setName("Fleece Pullover Hoodie").setImageUrl("https://static.footshop.com/411985/85273.jpg").
                    setBrand(nike).setDescription("80% Cotton - A very soft and pleasant material whose properties include tensile strength and elongation at break. Unlike other materials, it does not get damaged easily when washed often.");

            ClothingEntity joggersBB = new ClothingEntity();
            joggersBB.setName("Joggers BB").setImageUrl("https://static.footshop.com/602353/115462.jpg").
                    setBrand(nike).setDescription("80% Cotton - A very soft and pleasant material whose properties include tensile strength and elongation at break. Unlike other materials, it does not get damaged easily when washed often.\n" +
                    "        //20% Polyester - a very strong synthetic fiber that boasts high heat resistance and excellent odor absorption");

            ClothingEntity threeStripesPants = new ClothingEntity();

            threeStripesPants.setName("3-Stripes Pants").setImageUrl("https://static.footshop.com/489010/80728.jpg").setBrand(adidas).
                    setDescription("Straightforwardly adidas. Keep your look low-key and legit in these pants. Contrast 3-Stripes and a Trefoil logo are as OG as it gets. Feel cosy and warm in fleece. Secure your stuff in zip pockets. And we're done here. Our cotton products support sustainable cotton farming. This product is also made with recycled content as part of our ambition to end plastic waste.");

            ClothingEntity trefoilShirt= new ClothingEntity();

            trefoilShirt.setName("Trefoil T-Shirt ").setImageUrl("https://static.footshop.com/585583/126220.jpg").setBrand(adidas).
                    setDescription("The Trefoil logo is steeped in sport and style history. But it's about more than that. It represents a fearless energy and a clan of creators striving to be their best at every step. Slip into the comfort of this adidas t-shirt and show it off.");


            ClothingEntity michelBasquiat = new ClothingEntity();

            michelBasquiat.setName("Michel Basquiat Elevated Tee").setImageUrl("https://static.footshop.com/613243/166303.jpg").setBrand(converse).
setDescription("A very soft and pleasant material whose properties include tensile strength and elongation at break. Unlike other materials, it does not get damaged easily when washed often.");

            ClothingEntity jacket = new ClothingEntity();

            jacket.setName("Telegraphic Coaches Jacket").setImageUrl("https://static.footshop.com/613657/71026.jpg").setBrand(theNorthFace).
setDescription("Never a look to go out of style, the Telegraphic Coaches Jacket also happens to be durable enough to last the test of time. The material also blocks out wind and can handle a light shower or two.");

            clothingRepository.saveAll(List.of(clubTee, pullOverHoodie, joggersBB,threeStripesPants,trefoilShirt,michelBasquiat,jacket));
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

        clothingEntity.setName(serviceModel.getName()).setImageUrl(serviceModel.getImageUrl()).
                setDescription(serviceModel.getDescription());
            clothingRepository.save(clothingEntity);

    }

}
