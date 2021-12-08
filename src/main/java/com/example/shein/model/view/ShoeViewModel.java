package com.example.shein.model.view;

import java.math.BigDecimal;

public class ShoeViewModel {

    private Long id;
    private String name;
    private BigDecimal price;
    private String brand;
    private String imageUrl;

    public Long getId() {
        return id;
    }

    public ShoeViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ShoeViewModel setName(String name) {
        this.name = name;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public ShoeViewModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public String getBrand() {
        return brand;
    }

    public ShoeViewModel setBrand(String brand) {
        this.brand = brand;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public ShoeViewModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }
}
