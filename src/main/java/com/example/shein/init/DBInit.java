package com.example.shein.init;

import com.example.shein.service.ClothingService;
import com.example.shein.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DBInit implements CommandLineRunner {


    private final UserService userService;
    private final ClothingService clothingService;

    public DBInit(UserService userService, ClothingService clothingService) {
        this.userService = userService;
        this.clothingService = clothingService;
    }

    @Override
    public void run(String... args) throws Exception {
        userService.initUsersAndRoles();
        clothingService.initClothes();
    }
}
