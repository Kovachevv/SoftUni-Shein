package com.example.shein.init;

import com.example.shein.service.BrandService;
import com.example.shein.service.ClothingService;
import com.example.shein.service.ShoeService;
import com.example.shein.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DBInit implements CommandLineRunner {


    private final UserService userService;
    private final ClothingService clothingService;
    private final BrandService brandService;
    private final ShoeService shoeService;


    public DBInit(UserService userService, ClothingService clothingService, BrandService brandService, ShoeService shoeService) {
        this.userService = userService;
        this.clothingService = clothingService;
        this.brandService = brandService;
        this.shoeService = shoeService;
    }

    @Override
    public void run(String... args) throws Exception {
        userService.initUsersAndRoles();
        brandService.initBrands();
        clothingService.initClothes();
        shoeService.initShoes();

    }
}
