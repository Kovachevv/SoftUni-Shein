package com.example.shein.model.entity;


import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "clothes")
public class ClothingEntity extends BaseEntity {

    @Column(nullable = false,unique = true)
    private String name;
    @Column(nullable = false)
    private BigDecimal price;
    @Column(columnDefinition = "TEXT")
    private String description;
    @ManyToOne
    private BrandEntity brand;
    @Column(nullable = false)
    private String imageUrl;

    public ClothingEntity() {
    }

    public String getName() {
        return name;
    }

    public ClothingEntity setName(String name) {
        this.name = name;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public ClothingEntity setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ClothingEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public BrandEntity getBrand() {
        return brand;
    }

    public ClothingEntity setBrand(BrandEntity brand) {
        this.brand = brand;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public ClothingEntity setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }
}
