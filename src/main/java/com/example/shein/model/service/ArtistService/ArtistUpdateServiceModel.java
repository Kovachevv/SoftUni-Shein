package com.example.shein.model.service.ArtistService;

public class ArtistUpdateServiceModel {

    private Long id;
    private String name;
    private String description;
    private String imageUrl;

    public Long getId() {
        return id;
    }

    public ArtistUpdateServiceModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ArtistUpdateServiceModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ArtistUpdateServiceModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public ArtistUpdateServiceModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }
}
