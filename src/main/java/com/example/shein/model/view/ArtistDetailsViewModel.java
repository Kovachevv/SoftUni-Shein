package com.example.shein.model.view;

import java.math.BigDecimal;

public class ArtistDetailsViewModel {

    private Long id;
    private String name;
    private String description;
    private boolean canDelete;
    private String imageUrl;

    public Long getId() {
        return id;
    }

    public ArtistDetailsViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ArtistDetailsViewModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ArtistDetailsViewModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public boolean isCanDelete() {
        return canDelete;
    }

    public ArtistDetailsViewModel setCanDelete(boolean canDelete) {
        this.canDelete = canDelete;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public ArtistDetailsViewModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }
}
