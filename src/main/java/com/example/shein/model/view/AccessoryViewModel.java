package com.example.shein.model.view;

import java.math.BigDecimal;

public class AccessoryViewModel {

    private Long id;
    private String name;
    private String brand;
    private String imageUrl;

    public Long getId() {
        return id;
    }

    public AccessoryViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public AccessoryViewModel setName(String name) {
        this.name = name;
        return this;
    }



    public String getBrand() {
        return brand;
    }

    public AccessoryViewModel setBrand(String brand) {
        this.brand = brand;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public AccessoryViewModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }
}
