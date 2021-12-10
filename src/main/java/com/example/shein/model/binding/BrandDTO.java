package com.example.shein.model.binding;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class BrandDTO {

    private Long id;
    @Size(min=3, max=20)
    @NotBlank
    private String name;
    @NotBlank
    @Size(min=3)
    private String description;
    @NotBlank
    private String imageUrl;

    public Long getId() {
        return id;
    }

    public BrandDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public BrandDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public BrandDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public BrandDTO setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }
}
