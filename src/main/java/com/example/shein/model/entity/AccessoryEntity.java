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

    public void setModel(String model) {
        this.model = model;
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
}
