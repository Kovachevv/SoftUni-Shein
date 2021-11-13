package com.example.shein.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "accesories")
public class AccessoryEntity extends BaseEntity{

    @Column(nullable = false)
    private String model;
    @Column(nullable = false)
    private BigDecimal price;
    @Column(columnDefinition = "TEXT",nullable = false)
    private String description;

    public AccessoryEntity() {
    }

    public String getModel() {
        return model;
    }

    public AccessoryEntity setModel(String model) {
        this.model = model;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public AccessoryEntity setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public AccessoryEntity setDescription(String description) {
        this.description = description;
        return this;
    }
}
