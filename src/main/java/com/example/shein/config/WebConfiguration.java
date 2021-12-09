package com.example.shein.config;

import com.example.shein.web.Interceptor.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    private final BrandInterceptor brandInterceptor;
    private final ClothingInterceptor clothingInterceptor;
    private final AccessoriesInterceptor accessoriesInterceptor;
    private final ShoesInterceptor shoesInterceptor;
    private final AlbumInterceptor albumInterceptor;
    private final ArtistInterceptor artistInterceptor;

    public WebConfiguration(BrandInterceptor brandInterceptor, ClothingInterceptor clothingInterceptor, AccessoriesInterceptor accessoriesInterceptor, ShoesInterceptor shoesInterceptor, AlbumInterceptor albumInterceptor, ArtistInterceptor artistInterceptor) {
        this.brandInterceptor = brandInterceptor;
        this.clothingInterceptor = clothingInterceptor;
        this.accessoriesInterceptor = accessoriesInterceptor;
        this.shoesInterceptor = shoesInterceptor;
        this.albumInterceptor = albumInterceptor;
        this.artistInterceptor = artistInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(brandInterceptor).addPathPatterns("/brands/**");
        registry.addInterceptor(clothingInterceptor).addPathPatterns("/clothes/**");
        registry.addInterceptor(accessoriesInterceptor).addPathPatterns("/accessories/**");
        registry.addInterceptor(shoesInterceptor).addPathPatterns("/shoes/**");
        registry.addInterceptor(albumInterceptor).addPathPatterns("/albums/**");
        registry.addInterceptor(artistInterceptor).addPathPatterns("/artists/**");
    }
}
