package com.example.shein.model.service.AlbumService;

import com.example.shein.model.entity.ArtistEntity;
import com.example.shein.model.entity.BrandEntity;

import java.math.BigDecimal;
import java.time.LocalDate;

public class AlbumAddServiceModel {

    private Long id;
    private Long artistId;
    private String name;
    private String description;
    private ArtistEntity artist;
    private String imageUrl;

    public Long getId() {
        return id;
    }

    public AlbumAddServiceModel setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getArtistId() {
        return artistId;
    }

    public AlbumAddServiceModel setArtistId(Long artistId) {
        this.artistId = artistId;
        return this;
    }

    public String getName() {
        return name;
    }

    public AlbumAddServiceModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public AlbumAddServiceModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public ArtistEntity getArtist() {
        return artist;
    }

    public AlbumAddServiceModel setArtist(ArtistEntity artist) {
        this.artist = artist;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public AlbumAddServiceModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }
}
