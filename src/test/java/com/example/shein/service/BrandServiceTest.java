package com.example.shein.service;

import com.example.shein.model.entity.BrandEntity;
import com.example.shein.repository.BrandRepository;
import com.example.shein.repository.UserRepository;
import com.example.shein.service.impl.BrandServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class BrandServiceTest {

    private BrandServiceImpl brandServiceToTest;

    @Mock
    private ModelMapper modelMapperToTest;

    @Mock
    private BrandRepository mockBrandRepository;
    @Mock
    private UserRepository mockUserRepository;

    @BeforeEach
    void init(){
        brandServiceToTest = new BrandServiceImpl(mockBrandRepository,mockUserRepository,modelMapperToTest);

        BrandEntity nike = new BrandEntity();
        nike.setName("Nike").setImageUrl("https://c.static-nike.com/a/images/w_1920,c_limit/bzl2wmsfh7kgdkufrrjq/image.jpg").
                setDescription("Nike is an icon born under the name Blue Ribbon Sports in Oregon, USA, in 1972. Since its establishment until today, the brand has produced sneakers and apparel that push the limits every day. Nike Air Max, Huarache, Air Force or Roshe are just an example of what we offer.");
        BrandEntity adidas = new BrandEntity();
        adidas.setName("Adidas").setImageUrl("https://logos-world.net/wp-content/uploads/2020/04/Adidas-Symbol.png").
                setDescription("The legacy, history and legend that created the whole culture around sneakers. adidas Originals has been dominating the field of men''s fashion, sneakers and sportswear for decades. Why? Because behind the unceasing progress of the Originals lies proven quality, reliable materials and innovation dressed in the best of the past. Read on to find out why adidas Originals sneakers and shoe wear are so popular today and how they managed to dominate almost all men''s footwear.");

        mockBrandRepository.saveAll(List.of(nike,adidas));

    }

   /* @Test
    public void testInitBrands(){

        Mockito.when(mockBrandRepository.count()).thenReturn(2L);

        brandServiceToTest.initBrands();
        int expected = 2;
        Assertions.assertEquals(expected,mockBrandRepository.count());

    }*/

    @Test
   public void testAddBrand() {
       Assertions.assertTrue(mockBrandRepository.findByName("Nike").isPresent());
    }


}
