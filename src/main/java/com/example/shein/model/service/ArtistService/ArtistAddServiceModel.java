package com.example.shein.model.service.ArtistService;

public class ArtistAddServiceModel {

    private String name;
    private String description;
    private String imageUrl;

    public String getName() {
        return name;
    }

    public ArtistAddServiceModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ArtistAddServiceModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public ArtistAddServiceModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }
}
