package com.example.shein.model.entity;


import com.example.shein.model.enums.ClothingTypeEnum;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "clothes")
public class ClothingEntity extends BaseEntity {

    @Column(nullable = false,unique = true)
    private String model;
    @Column(nullable = false)
    private BigDecimal price;
    @Column(columnDefinition = "TEXT")
    private String description;
    @ManyToOne
    private BrandEntity brand;
    @Column(nullable = false)
    private ClothingTypeEnum type;
    private String image;

    public ClothingEntity() {
    }

    public String getModel() {
        return model;
    }

    public ClothingEntity setModel(String model) {
        this.model = model;
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

    public ClothingTypeEnum getType() {
        return type;
    }

    public ClothingEntity setType(ClothingTypeEnum type) {
        this.type = type;
        return this;
    }

    public String getImage() {
        return image;
    }

    public ClothingEntity setImage(String image) {
        this.image = image;
        return this;
    }
}
