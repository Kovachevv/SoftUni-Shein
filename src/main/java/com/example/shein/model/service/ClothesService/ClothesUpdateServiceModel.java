package com.example.shein.model.service.ClothesService;

import com.example.shein.model.entity.BrandEntity;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

public class ClothesUpdateServiceModel {

    private Long id;
    private String name;
    private BigDecimal price;
    private String description;
    private String imageUrl;

    public Long getId() {
        return id;
    }

    public ClothesUpdateServiceModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ClothesUpdateServiceModel setName(String name) {
        this.name = name;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public ClothesUpdateServiceModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ClothesUpdateServiceModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public ClothesUpdateServiceModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }
}
