package com.example.shein.model.binding;

import com.example.shein.model.entity.AlbumEntity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.Set;

public class ArtistDTO {


    private Long id;
    private String name;
    private String description;
    private String imageUrl;
    private Set<AlbumEntity> albums;

    public String getImageUrl() {
        return imageUrl;
    }

    public ArtistDTO setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public Long getId() {
        return id;
    }

    public ArtistDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ArtistDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ArtistDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public Set<AlbumEntity> getAlbums() {
        return albums;
    }

    public ArtistDTO setAlbums(Set<AlbumEntity> albums) {
        this.albums = albums;
        return this;
    }
}
