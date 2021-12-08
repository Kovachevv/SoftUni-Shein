package com.example.shein.model.service.ShoeService;

import java.math.BigDecimal;

public class ShoeUpdateServiceModel {

    private Long id;
    private String name;
    private BigDecimal price;
    private String description;
    private String imageUrl;

    public Long getId() {
        return id;
    }

    public ShoeUpdateServiceModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ShoeUpdateServiceModel setName(String name) {
        this.name = name;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public ShoeUpdateServiceModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ShoeUpdateServiceModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public ShoeUpdateServiceModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }
}