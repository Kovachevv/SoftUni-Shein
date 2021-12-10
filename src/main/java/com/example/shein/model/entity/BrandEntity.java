package com.example.shein.model.entity;


import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.*;


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
    @OneToMany(mappedBy = "brand", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<ClothingEntity> clothes = new ArrayList<>();
    @OneToMany(mappedBy = "brand", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<ShoeEntity> shoes = new ArrayList<>();
    @OneToMany(mappedBy = "brand", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<AccessoryEntity> accessories = new ArrayList<>();

    public List<ShoeEntity> getShoes() {
        return shoes;
    }

    public BrandEntity setShoes(List<ShoeEntity> shoes) {
        this.shoes = shoes;
        return this;
    }

    public List<AccessoryEntity> getAccessories() {
        return accessories;
    }

    public BrandEntity setAccessories(List<AccessoryEntity> accessories) {
        this.accessories = accessories;
        return this;
    }

    public List<ClothingEntity> getClothes() {
        return clothes;
    }

    public BrandEntity setClothes(List<ClothingEntity> clothes) {
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
