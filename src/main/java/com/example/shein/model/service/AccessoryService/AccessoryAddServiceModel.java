package com.example.shein.model.service.AccessoryService;

import com.example.shein.model.entity.BrandEntity;

import java.math.BigDecimal;

public class AccessoryAddServiceModel {

    private Long id;
    private Long brandId;
    private String name;
    private BigDecimal price;
    private String description;
    private BrandEntity brand;
    private String imageUrl;

    public Long getId() {
        return id;
    }

    public AccessoryAddServiceModel setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getBrandId() {
        return brandId;
    }

    public AccessoryAddServiceModel setBrandId(Long brandId) {
        this.brandId = brandId;
        return this;
    }

    public String getName() {
        return name;
    }

    public AccessoryAddServiceModel setName(String name) {
        this.name = name;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public AccessoryAddServiceModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public AccessoryAddServiceModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public BrandEntity getBrand() {
        return brand;
    }

    public AccessoryAddServiceModel setBrand(BrandEntity brand) {
        this.brand = brand;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public AccessoryAddServiceModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }
}
