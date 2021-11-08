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
    private String color;
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

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }


    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BrandEntity getBrand() {
        return brand;
    }

    public void setBrand(BrandEntity brand) {
        this.brand = brand;
    }

    public ClothingTypeEnum getType() {
        return type;
    }

    public void setType(ClothingTypeEnum type) {
        this.type = type;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
