package com.example.shein.service.impl;

import com.example.shein.model.entity.*;
import com.example.shein.model.enums.UserRoleEnum;
import com.example.shein.model.service.ShoeService.ShoeAddServiceModel;
import com.example.shein.model.service.ShoeService.ShoeUpdateServiceModel;
import com.example.shein.model.view.ClothingDetailsView;
import com.example.shein.model.view.ShoeDetailsView;
import com.example.shein.model.view.ShoeViewModel;
import com.example.shein.repository.BrandRepository;
import com.example.shein.repository.ShoeRepository;
import com.example.shein.repository.UserRepository;
import com.example.shein.service.ShoeService;
import javassist.tools.rmi.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.*;
import java.math.BigDecimal;
import java.util.stream.Collectors;

@Service
public class ShoeServiceImpl implements ShoeService {

    private final ShoeRepository shoeRepository;
    private final BrandRepository brandRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    public ShoeServiceImpl(ShoeRepository shoeRepository, BrandRepository brandRepository, ModelMapper modelMapper, UserRepository userRepository) {
        this.shoeRepository = shoeRepository;
        this.brandRepository = brandRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }

    @Override
    public List<ShoeViewModel> getAllShoes() {
        return shoeRepository.findAll().stream().map(shoeEntity -> {
            ShoeViewModel shoeViewModel = this.modelMapper.map(shoeEntity, ShoeViewModel.class);
            shoeViewModel.setBrand(shoeEntity.getBrand().getName());

            return shoeViewModel;
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
    public ShoeDetailsView findById(Long id, String username) {
        return shoeRepository.findById(id).map(shoeEntity -> {
            ShoeDetailsView shoeDetailsView = this.modelMapper.map(shoeEntity, ShoeDetailsView.class);
            shoeDetailsView.setBrand(shoeEntity.getBrand().getName()).setCanDelete(isAdmin(username));
            return shoeDetailsView;
        }).get();
    }

    @Override
    public void initShoes() {
        if (shoeRepository.count() == 0) {

            BrandEntity nike = brandRepository.findByName("Nike").orElseThrow(IllegalArgumentException::new);
            BrandEntity adidas = brandRepository.findByName("Adidas").orElseThrow(IllegalArgumentException::new);

            ShoeEntity airForce1 = new ShoeEntity();
            airForce1.setBrand(nike).setName("Air Force 1").setPrice(BigDecimal.valueOf(99.95)).setImageUrl("https://static.footshop.com/564661/130801.jpg").setDescription("Named after the personal aircraft of the President of the United States, the Air Force One has been a mainstay on the Nike roster. They are among the longest-produced sneakers of all time and are one of the most popular Nike shoes off all time. The AF 1 low's come in a variety of different and striking colourways perfect for all tastes.");
            ShoeEntity airMax = new ShoeEntity();
            airMax.setBrand(nike).setName("Air Max 97").setPrice(BigDecimal.valueOf(162.95)).setImageUrl("https://static.footshop.com/424660/18972.jpg").setDescription("As the name suggests, it was 1997 when the Nike Air Max 97 futuristic sneakers saw light. Thanks to their timeless uppers, they instantly attracted attention and became a living legend so soon. The shock-absorbing air bubble is spread below the whole foot in this model to improve the overall impression of the sneaker.");

            shoeRepository.saveAll(List.of(airForce1, airMax));

        }
    }

    @Override
    public void addShoe(ShoeAddServiceModel serviceModel) {
        ShoeEntity shoeEntity = modelMapper.map(serviceModel, ShoeEntity.class);
        shoeRepository.save(shoeEntity);

    }

    @Override
    public void deleteShoe(Long id) {
        shoeRepository.deleteById(id);
    }

    @Override
    public void updateShoe(ShoeUpdateServiceModel serviceModel) throws ObjectNotFoundException {

        ShoeEntity shoeEntity = shoeRepository.findById(serviceModel.getId()).orElseThrow(() ->
                new ObjectNotFoundException("Shoe with id " + serviceModel.getId() + " not found!"));
        shoeEntity.setName(serviceModel.getName()).setPrice(serviceModel.getPrice()).setImageUrl(serviceModel.getImageUrl()).
                setDescription(serviceModel.getDescription());
        shoeRepository.save(shoeEntity);

    }
}
