package com.example.shein.service.impl;

import com.example.shein.model.entity.*;
import com.example.shein.model.enums.UserRoleEnum;
import com.example.shein.model.service.AccessoryService.AccessoryAddServiceModel;
import com.example.shein.model.service.AccessoryService.AccessoryUpdateServiceModel;
import com.example.shein.model.view.AccessoryDetailsViewModel;
import com.example.shein.model.view.AccessoryViewModel;
import com.example.shein.repository.AccessoryRepository;
import com.example.shein.repository.BrandRepository;
import com.example.shein.repository.UserRepository;
import com.example.shein.service.AccessoryService;
import javassist.tools.rmi.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccessoryServiceImpl implements AccessoryService {

    private final AccessoryRepository accessoryRepository;
    private final ModelMapper modelMapper;
    private final BrandRepository brandRepository;
    private final UserRepository userRepository;

    public AccessoryServiceImpl(AccessoryRepository accessoryRepository, ModelMapper modelMapper, BrandRepository brandRepository, UserRepository userRepository) {
        this.accessoryRepository = accessoryRepository;
        this.modelMapper = modelMapper;
        this.brandRepository = brandRepository;
        this.userRepository = userRepository;
    }


    @Override
    public List<AccessoryViewModel> getAllAccessories() {
        return accessoryRepository.findAll().stream().map(accessoryEntity -> {

            AccessoryViewModel accessoryViewModel = this.modelMapper.map(accessoryEntity,AccessoryViewModel.class);
            accessoryViewModel.setBrand(accessoryEntity.getBrand().getName());

            return accessoryViewModel;

        }).collect(Collectors.toList());



    }

    @Override
    public void initAccessories() {
        if (accessoryRepository.count() == 0) {

            BrandEntity nike = brandRepository.findByName("Nike").orElseThrow(IllegalArgumentException::new);
            BrandEntity adidas = brandRepository.findByName("Adidas").orElseThrow(IllegalArgumentException::new);
            AccessoryEntity sportsHeritage = new AccessoryEntity();
            sportsHeritage.setBrand(nike).setName("Sportswear Heritage").setPrice(BigDecimal.valueOf(27.95)).setImageUrl("https://static.nike.com/a/images/t_PDP_864_v1/f_auto,b_rgb:f5f5f5/c7ee3000-8ff9-4955-8dd5-2aec869d12af/sportswear-heritage-winterized-backpack-x1t0l4.png").setDescription("The Nike Sportswear Heritage Backpack features reinforced panels to help protect your gear from the elements. Oversized pulls allow you to open up your pack with gloves so you can keep moving even when it feels like the world is frozen solid.");

            AccessoryEntity faceCover = new AccessoryEntity();
            faceCover.setBrand(adidas).setName("Face Cover").setPrice(BigDecimal.valueOf(16)).setImageUrl("https://static.footshop.com/487054/104458.jpg").setDescription("Phone, keys, wallet and face mask. The new leaving-the-house essential, this adidas face cover makes it easy to practise healthy habits. The seamless inner panel and stretchy ear loops keep you comfortable every day.");

            accessoryRepository.saveAll(List.of(sportsHeritage,faceCover));

        }
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
    public AccessoryDetailsViewModel findById(Long id, String username) {
        return accessoryRepository.findById(id).map(accessoryEntity -> {
            AccessoryDetailsViewModel accessoryDetailsViewModel = this.modelMapper.map(accessoryEntity,AccessoryDetailsViewModel.class);
            accessoryDetailsViewModel.setBrand(accessoryEntity.getBrand().getName()).setCanDelete(isAdmin(username));
            return accessoryDetailsViewModel;
        }).get();
    }

    @Override
    public void addAccessory(AccessoryAddServiceModel serviceModel) {
        AccessoryEntity accessoryEntity = modelMapper.map(serviceModel,AccessoryEntity.class);
        accessoryRepository.save(accessoryEntity);

    }

    @Override
    public void deleteAccessory(Long id) {
        accessoryRepository.deleteById(id);
    }

    @Override
    public void updateAccessory(AccessoryUpdateServiceModel serviceModel) throws ObjectNotFoundException {

        AccessoryEntity accessoryEntity = accessoryRepository.findById(serviceModel.getId()).orElseThrow(()->
                new ObjectNotFoundException("Accessory with id " + serviceModel.getId() + " not found!"));
                accessoryEntity.setName(serviceModel.getName()).setPrice(serviceModel.getPrice()).setImageUrl(serviceModel.getImageUrl()).
                 setDescription(serviceModel.getDescription());
                accessoryRepository.save(accessoryEntity);

    }
}
