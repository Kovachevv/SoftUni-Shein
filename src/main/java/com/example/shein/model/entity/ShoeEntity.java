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

    public ShoeEntity setModel(String model) {
        this.model = model;
        return this;
    }
    public BigDecimal getPrice() {
        return price;
    }

    public ShoeEntity setPrice(BigDecimal price) {
        this.price = price;
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

    public ShoeTypeEnum getType() {
        return type;
    }

    public ShoeEntity setType(ShoeTypeEnum type) {
        this.type = type;
        return this;
    }

    public String getImage() {
        return image;
    }

    public ShoeEntity setImage(String image) {
        this.image = image;
        return this;
    }
}
