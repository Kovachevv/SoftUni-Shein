package com.example.shein.model.binding;

import com.example.shein.model.entity.BrandEntity;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

public class ClothesDTO {

    private Long id;
    @NotNull
    private Long brandId;
    @NotBlank
    @Size(min=3)
    private String name;
    @NotBlank
    @Size(min = 10)
    private String description;
    @NotBlank
    private String imageUrl;

    public Long getId() {
        return id;
    }

    public ClothesDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getBrandId() {
        return brandId;
    }

    public ClothesDTO setBrandId(Long brandId) {
        this.brandId = brandId;
        return this;
    }

    public String getName() {
        return name;
    }

    public ClothesDTO setName(String name) {
        this.name = name;
        return this;
    }


    public String getDescription() {
        return description;
    }

    public ClothesDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public ClothesDTO setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }
}
