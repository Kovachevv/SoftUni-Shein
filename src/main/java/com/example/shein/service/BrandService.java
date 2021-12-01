package com.example.shein.service;

import com.example.shein.model.service.BrandAddServiceModel;
import com.example.shein.model.view.BrandDetailsViewModel;
import com.example.shein.model.view.BrandViewModel;

import java.util.*;

public interface BrandService {
    List<BrandViewModel> getAllBrands();
    BrandDetailsViewModel findById(Long id);
    void addBrand(BrandAddServiceModel brandAddServiceModel);
}
