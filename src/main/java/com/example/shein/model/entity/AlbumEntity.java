package com.example.shein.model.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "albums")
public class AlbumEntity extends BaseEntity{

    @Column(nullable = false)
    private String name;
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private ArtistEntity artist;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;
    private String imageUrl;

    public AlbumEntity() {
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public AlbumEntity setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public String getName() {
        return name;
    }

    public AlbumEntity setName(String name) {
        this.name = name;
        return this;
    }


    public ArtistEntity getArtist() {
        return artist;
    }

    public AlbumEntity setArtist(ArtistEntity artist) {
        this.artist = artist;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public AlbumEntity setDescription(String description) {
        this.description = description;
        return this;
    }
}
