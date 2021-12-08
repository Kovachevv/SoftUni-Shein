package com.example.shein.init;

import com.example.shein.service.BrandService;
import com.example.shein.service.ClothingService;
import com.example.shein.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DBInit implements CommandLineRunner {


    private final UserService userService;
    private final ClothingService clothingService;
    private final BrandService brandService;


    public DBInit(UserService userService, ClothingService clothingService, BrandService brandService) {
        this.userService = userService;
        this.clothingService = clothingService;
        this.brandService = brandService;
    }

    @Override
    public void run(String... args) throws Exception {
        userService.initUsersAndRoles();
        brandService.initBrands();
        clothingService.initClothes();

    }
}
