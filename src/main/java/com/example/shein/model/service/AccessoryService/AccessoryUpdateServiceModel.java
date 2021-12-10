package com.example.shein.model.service.AccessoryService;

import java.math.BigDecimal;

public class AccessoryUpdateServiceModel {

    private Long id;
    private String name;

    private String description;
    private String imageUrl;

    public Long getId() {
        return id;
    }

    public AccessoryUpdateServiceModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public AccessoryUpdateServiceModel setName(String name) {
        this.name = name;
        return this;
    }


    public String getDescription() {
        return description;
    }

    public AccessoryUpdateServiceModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public AccessoryUpdateServiceModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }
}
