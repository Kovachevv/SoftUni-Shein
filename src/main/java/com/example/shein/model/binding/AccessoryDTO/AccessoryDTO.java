package com.example.shein.model.binding.AccessoryDTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AccessoryDTO {

    private Long id;
    @NotNull
    private Long brandId;
    @NotBlank
    @Size(min=3)
    private String name;
    @NotBlank
    @Size(min = 3)
    private String description;
    @NotBlank
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
