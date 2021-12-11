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

            AccessoryViewModel accessoryViewModel = this.modelMapper.map(accessoryEntity, AccessoryViewModel.class);
            accessoryViewModel.setBrand(accessoryEntity.getBrand().getName());

            return accessoryViewModel;

        }).collect(Collectors.toList());


    }

    @Override
    public void initAccessories() {
        if (accessoryRepository.count() == 0) {

            BrandEntity nike = brandRepository.findByName("Nike").orElseThrow(IllegalArgumentException::new);
            BrandEntity adidas = brandRepository.findByName("Adidas").orElseThrow(IllegalArgumentException::new);
            BrandEntity vans = brandRepository.findByName("Vans").orElseThrow(IllegalArgumentException::new);

            AccessoryEntity sportsHeritage = new AccessoryEntity();
            sportsHeritage.setBrand(nike).setName("Sportswear Heritage").setImageUrl("https://static.footshop.com/510976/113824.jpg").setDescription("The Nike Sportswear Heritage Backpack features reinforced panels to help protect your gear from the elements. Oversized pulls allow you to open up your pack with gloves so you can keep moving even when it feels like the world is frozen solid.");

            AccessoryEntity faceCover = new AccessoryEntity();
            faceCover.setBrand(adidas).setName("Face Cover").setImageUrl("https://static.footshop.com/487054/104458.jpg").setDescription("Phone, keys, wallet and face mask. The new leaving-the-house essential, this adidas face cover makes it easy to practise healthy habits. The seamless inner panel and stretchy ear loops keep you comfortable every day.");

            AccessoryEntity beanie = new AccessoryEntity();
            beanie.setBrand(vans).setName("Shallow Cuff Beanie").setImageUrl("https://static.footshop.com/617788/179095.jpg").
                    setDescription("A fabric that is soft, doesn’t crease, dries quickly, but mainly, it is not enjoyable for moths.");


            AccessoryEntity socks = new AccessoryEntity();
            socks.setBrand(vans).setName("Dive Bar Crew Socks").setImageUrl("https://static.footshop.com/574156/124429.jpg").
                    setDescription("A very soft and pleasant material whose properties include tensile strength and elongation at break. Unlike other materials, it does not get damaged easily when washed often.");


            accessoryRepository.saveAll(List.of(sportsHeritage, faceCover,beanie,socks));

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
            AccessoryDetailsViewModel accessoryDetailsViewModel = this.modelMapper.map(accessoryEntity, AccessoryDetailsViewModel.class);
            accessoryDetailsViewModel.setBrand(accessoryEntity.getBrand().getName()).setCanDelete(isAdmin(username));
            return accessoryDetailsViewModel;
        }).get();
    }

    @Override
    public void addAccessory(AccessoryAddServiceModel serviceModel) {
        AccessoryEntity accessoryEntity = modelMapper.map(serviceModel, AccessoryEntity.class);
        accessoryRepository.save(accessoryEntity);

    }

    @Override
    public void deleteAccessory(Long id) {
        accessoryRepository.deleteById(id);
    }

    @Override
    public void updateAccessory(AccessoryUpdateServiceModel serviceModel) throws ObjectNotFoundException {

        AccessoryEntity accessoryEntity = accessoryRepository.findById(serviceModel.getId()).orElseThrow(() ->
                new ObjectNotFoundException("Accessory with id " + serviceModel.getId() + " not found!"));
        accessoryEntity.setName(serviceModel.getName()).setImageUrl(serviceModel.getImageUrl()).
                setDescription(serviceModel.getDescription());

        accessoryRepository.save(accessoryEntity);

    }
}
