package com.example.shein.service;

import com.example.shein.model.view.BrandDetailsViewModel;
import com.example.shein.model.view.BrandViewModel;

import java.util.*;

public interface BrandService {
    List<BrandViewModel> getAllBrands();
    BrandViewModel findById(Long id, String user);
}
