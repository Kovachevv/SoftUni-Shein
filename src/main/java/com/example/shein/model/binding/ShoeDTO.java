package com.example.shein.model.binding;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

public class ShoeDTO {

    private Long id;
    private Long brandId;
    @NotNull
    @Size(min=3)
    private String name;
    @Positive
    @NotNull
    private BigDecimal price;
    @NotNull
    private String description;
    @NotNull
    private String imageUrl;

    public Long getId() {
        return id;
    }

    public ShoeDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getBrandId() {
        return brandId;
    }

    public ShoeDTO setBrandId(Long brandId) {
        this.brandId = brandId;
        return this;
    }

    public String getName() {
        return name;
    }

    public ShoeDTO setName(String name) {
        this.name = name;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public ShoeDTO setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ShoeDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public ShoeDTO setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }
}
