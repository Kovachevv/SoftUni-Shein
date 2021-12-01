package com.example.shein.model.binding;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class BrandDTO {

    @Size(min=3, max=20)
    private String name;
    @NotNull
    @Size(min=3)
    private String description;
    @NotNull
    private String imageUrl;

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
