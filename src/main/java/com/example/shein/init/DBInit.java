package com.example.shein.init;

import com.example.shein.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DBInit implements CommandLineRunner {


    private final UserService userService;
    private final ClothingService clothingService;
    private final BrandService brandService;
    private final ShoeService shoeService;
    private final AccessoryService accessoryService;
    private final ArtistService artistService;
    private final AlbumService albumService;


    public DBInit(UserService userService, ClothingService clothingService, BrandService brandService, ShoeService shoeService, AccessoryService accessoryService, ArtistService artistService, AlbumService albumService) {
        this.userService = userService;
        this.clothingService = clothingService;
        this.brandService = brandService;
        this.shoeService = shoeService;
        this.accessoryService = accessoryService;
        this.artistService = artistService;
        this.albumService = albumService;
    }

    @Override
    public void run(String... args) throws Exception {
        userService.initUsersAndRoles();
        brandService.initBrands();
        clothingService.initClothes();
        shoeService.initShoes();
        accessoryService.initAccessories();
        artistService.initArtists();
        albumService.initAlbums();
    }
}
