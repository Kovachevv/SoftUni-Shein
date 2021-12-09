package com.example.shein.model.service.AlbumService;

import java.math.BigDecimal;

public class AlbumUpdateServiceModel {

    private Long id;
    private String name;
    private String description;
    private String imageUrl;

    public Long getId() {
        return id;
    }

    public AlbumUpdateServiceModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public AlbumUpdateServiceModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public AlbumUpdateServiceModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public AlbumUpdateServiceModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }
}
