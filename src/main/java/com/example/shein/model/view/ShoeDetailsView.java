package com.example.shein.model.view;

import java.math.BigDecimal;

public class ShoeDetailsView {

    private Long id;
    private String name;
    private String description;
    private String brand;
    private boolean canDelete;
    private String imageUrl;

    public String getImageUrl() {
        return imageUrl;
    }

    public ShoeDetailsView setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public Long getId() {
        return id;
    }

    public ShoeDetailsView setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ShoeDetailsView setName(String name) {
        this.name = name;
        return this;
    }


    public String getDescription() {
        return description;
    }

    public ShoeDetailsView setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getBrand() {
        return brand;
    }

    public ShoeDetailsView setBrand(String brand) {
        this.brand = brand;
        return this;
    }

    public boolean isCanDelete() {
        return canDelete;
    }

    public ShoeDetailsView setCanDelete(boolean canDelete) {
        this.canDelete = canDelete;
        return this;
    }
}
