package com.example.shein.model.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class ArtistEntity extends BaseEntity{

    @Column(nullable = false)
    private String name;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;
    @OneToMany(mappedBy = "artist", cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Set<AlbumEntity> albums;
    private String imageUrl;

    public String getImageUrl() {
        return imageUrl;
    }

    public ArtistEntity setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public ArtistEntity() {
    }

    public String getName() {
        return name;
    }

    public ArtistEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ArtistEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public Set<AlbumEntity> getAlbums() {
        return albums;
    }

    public ArtistEntity setAlbums(Set<AlbumEntity> albums) {
        this.albums = albums;
        return this;
    }
}
