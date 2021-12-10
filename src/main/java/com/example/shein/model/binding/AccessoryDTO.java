package com.example.shein.model.binding;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

public class AccessoryDTO {

    private Long id;
    private Long brandId;
    @NotNull
    @Size(min=3)
    private String name;
    @Positive
    @NotNull
    private BigDecimal price;
    @NotNull
    @Size(min = 3)
    private String description;
    @NotNull
    private String imageUrl;

    public Long getId() {
        return id;
    }

    public AccessoryDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getBrandId() {
        return brandId;
    }

    public AccessoryDTO setBrandId(Long brandId) {
        this.brandId = brandId;
        return this;
    }

    public String getName() {
        return name;
    }

    public AccessoryDTO setName(String name) {
        this.name = name;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public AccessoryDTO setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public AccessoryDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public AccessoryDTO setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }
}
