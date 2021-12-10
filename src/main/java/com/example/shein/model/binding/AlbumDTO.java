package com.example.shein.model.binding;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class AlbumDTO {


    private Long id;
    private Long artistId;
    @NotNull
    @Size(min=2, max=30)
    private String name;
    @NotNull
    @Size(min=10)
    private String description;
    @NotNull
    private String imageUrl;

    public Long getArtistId() {
        return artistId;
    }

    public AlbumDTO setArtistId(Long artistId) {
        this.artistId = artistId;
        return this;
    }

    public Long getId() {
        return id;
    }

    public AlbumDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public AlbumDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public AlbumDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public AlbumDTO setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

}
