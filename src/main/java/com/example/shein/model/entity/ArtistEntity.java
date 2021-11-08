package com.example.shein.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
public class ArtistEntity extends BaseEntity{
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String description;
    @OneToMany
    private Set<AlbumEntity> albums;

    public ArtistEntity() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<AlbumEntity> getAlbums() {
        return albums;
    }

    public void setAlbums(Set<AlbumEntity> albums) {
        this.albums = albums;
    }
}
