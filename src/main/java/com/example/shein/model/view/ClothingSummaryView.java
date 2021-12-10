package com.example.shein.model.view;

import com.example.shein.model.entity.BrandEntity;

import java.math.BigDecimal;

public class ClothingSummaryView {

    private Long id;
    private String name;
    private String brand;
    private String imageUrl;

    public String getBrand() {
        return brand;
    }

    public ClothingSummaryView setBrand(String brand) {
        this.brand = brand;
        return this;
    }

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


    public String getImageUrl() {
        return imageUrl;
    }

    public ClothingSummaryView setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }
}
