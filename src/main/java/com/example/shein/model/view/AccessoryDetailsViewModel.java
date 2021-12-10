package com.example.shein.model.view;

import java.math.BigDecimal;

public class AccessoryDetailsViewModel {

    private Long id;
    private String name;
    private String description;
    private String brand;
    private boolean canDelete;
    private String imageUrl;

    public Long getId() {
        return id;
    }

    public AccessoryDetailsViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public AccessoryDetailsViewModel setName(String name) {
        this.name = name;
        return this;
    }



    public String getDescription() {
        return description;
    }

    public AccessoryDetailsViewModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getBrand() {
        return brand;
    }

    public AccessoryDetailsViewModel setBrand(String brand) {
        this.brand = brand;
        return this;
    }

    public boolean isCanDelete() {
        return canDelete;
    }

    public AccessoryDetailsViewModel setCanDelete(boolean canDelete) {
        this.canDelete = canDelete;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public AccessoryDetailsViewModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }
}
