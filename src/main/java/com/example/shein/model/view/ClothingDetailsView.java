package com.example.shein.model.view;

import com.example.shein.model.entity.BrandEntity;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

public class ClothingDetailsView {

    private String name;
    private BigDecimal price;
    private String description;
    private String brand;

    public String getBrand() {
        return brand;
    }

    public ClothingDetailsView setBrand(String brand) {
        this.brand = brand;
        return this;
    }

    private String imageUrl;

    public String getName() {
        return name;
    }

    public ClothingDetailsView setName(String name) {
        this.name = name;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public ClothingDetailsView setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ClothingDetailsView setDescription(String description) {
        this.description = description;
        return this;
    }


    public String getImageUrl() {
        return imageUrl;
    }

    public ClothingDetailsView setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }
}
