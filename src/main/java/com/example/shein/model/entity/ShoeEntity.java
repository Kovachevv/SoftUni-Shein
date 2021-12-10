package com.example.shein.model.entity;


import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "shoes")
public class ShoeEntity extends BaseEntity{

    @Column(nullable = false,unique = true)
    private String name;
    @Column(columnDefinition = "TEXT")
    private String description;
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private BrandEntity brand;
    @Column(nullable = false)
    private String imageUrl;


    public ShoeEntity() {
    }

    public String getName() {
        return name;
    }

    public ShoeEntity setName(String name) {
        this.name = name;
        return this;
    }


    public String getDescription() {
        return description;
    }

    public ShoeEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public BrandEntity getBrand() {
        return brand;
    }

    public ShoeEntity setBrand(BrandEntity brand) {
        this.brand = brand;
        return this;
    }


    public String getImageUrl() {
        return imageUrl;
    }

    public ShoeEntity setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }
}
