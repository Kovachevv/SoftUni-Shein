package com.example.shein.model.entity;


import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "brands")
public class BrandEntity extends BaseEntity{

    @Column(nullable = false)
    @Size(min=3, max=20)
    private String name;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;
    @Column(nullable = false)
    private String imageUrl;
    @OneToMany(mappedBy = "brand", cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Set<ClothingEntity> clothes;
    @OneToMany(mappedBy = "brand", cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Set<ShoeEntity> shoes;
    @OneToMany(mappedBy = "brand", cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Set<AccessoryEntity> accessories;

    public Set<ShoeEntity> getShoes() {
        return shoes;
    }

    public BrandEntity setShoes(Set<ShoeEntity> shoes) {
        this.shoes = shoes;
        return this;
    }

    public Set<ClothingEntity> getClothes() {
        return clothes;
    }

    public BrandEntity setClothes(Set<ClothingEntity> clothes) {
        this.clothes = clothes;
        return this;
    }

    public BrandEntity() {
    }

    public String getName() {
        return name;
    }

    public BrandEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public BrandEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public BrandEntity setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }
}
