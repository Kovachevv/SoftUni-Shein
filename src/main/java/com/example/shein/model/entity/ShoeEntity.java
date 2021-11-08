package com.example.shein.model.entity;


import com.example.shein.model.enums.ShoeTypeEnum;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "shoes")
public class ShoeEntity extends BaseEntity{

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
    private ShoeTypeEnum type;
    private String image;


    public ShoeEntity() {
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
