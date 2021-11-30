package com.example.shein.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
public class ArtistEntity extends BaseEntity{
    @Column(nullable = false)
    private String name;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;
    @OneToMany
    private Set<AlbumEntity> albums;

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
