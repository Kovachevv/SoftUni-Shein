package com.example.shein.model.view;

import com.example.shein.model.entity.BrandEntity;

import java.math.BigDecimal;

public class ClothingSummaryView {

    private Long id;
    private String name;
    private BigDecimal price;

    public String getBrand() {
        return brand;
    }

    public ClothingSummaryView setBrand(String brand) {
        this.brand = brand;
        return this;
    }

    private String brand;
    private String imageUrl;

    public Long getId() {
        return id;
    }

    public ClothingSummaryView setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ClothingSummaryView setName(String name) {
        this.name = name;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public ClothingSummaryView setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }



    public String getImageUrl() {
        return imageUrl;
    }

    public ClothingSummaryView setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }
}
