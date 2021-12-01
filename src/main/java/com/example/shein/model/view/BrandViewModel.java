package com.example.shein.model.view;

public class BrandViewModel {

    private String name;
    private String description;
    private String imageUrl;
    private Long id;

    public Long getId() {
        return id;
    }

    public BrandViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public BrandViewModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public BrandViewModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public BrandViewModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }
}
