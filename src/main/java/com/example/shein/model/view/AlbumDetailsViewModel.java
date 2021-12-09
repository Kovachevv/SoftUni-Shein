package com.example.shein.model.view;

import java.math.BigDecimal;

public class AlbumDetailsViewModel {

    private Long id;
    private String name;
    private String description;
    private String artist;
    private String imageUrl;
    private boolean canDelete;

    public String getImageUrl() {
        return imageUrl;
    }

    public AlbumDetailsViewModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public Long getId() {
        return id;
    }

    public AlbumDetailsViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public AlbumDetailsViewModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public AlbumDetailsViewModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getArtist() {
        return artist;
    }

    public AlbumDetailsViewModel setArtist(String artist) {
        this.artist = artist;
        return this;
    }

    public boolean isCanDelete() {
        return canDelete;
    }

    public AlbumDetailsViewModel setCanDelete(boolean canDelete) {
        this.canDelete = canDelete;
        return this;
    }
}
