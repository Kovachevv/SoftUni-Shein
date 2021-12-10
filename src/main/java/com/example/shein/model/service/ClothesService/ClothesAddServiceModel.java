package com.example.shein.model.service.ClothesService;

import com.example.shein.model.entity.BrandEntity;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

public class ClothesAddServiceModel {

    private Long id;
    private Long brandId;
    private String name;
    private String description;
    private BrandEntity brand;
    private String imageUrl;

    public Long getId() {
        return id;
    }

    public ClothesAddServiceModel setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getBrandId() {
        return brandId;
    }

    public ClothesAddServiceModel setBrandId(Long brandId) {
        this.brandId = brandId;
        return this;
    }

    public String getName() {
        return name;
    }

    public ClothesAddServiceModel setName(String name) {
        this.name = name;
        return this;
    }



    public String getDescription() {
        return description;
    }

    public ClothesAddServiceModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public BrandEntity getBrand() {
        return brand;
    }

    public ClothesAddServiceModel setBrand(BrandEntity brand) {
        this.brand = brand;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public ClothesAddServiceModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }
}
