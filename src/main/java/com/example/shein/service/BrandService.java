package com.example.shein.service;

import com.example.shein.model.entity.UserEntity;
import com.example.shein.model.service.BrandAddServiceModel;
import com.example.shein.model.service.BrandUpdateServiceModel;
import com.example.shein.model.view.BrandDetailsViewModel;
import com.example.shein.model.view.BrandViewModel;
import javassist.tools.rmi.ObjectNotFoundException;

import java.util.*;

public interface BrandService {
    List<BrandViewModel> getAllBrands();
    BrandDetailsViewModel findById(Long id,String username);
    void addBrand(BrandAddServiceModel brandAddServiceModel);
    void deleteBrand(Long id);
    boolean isAdmin(String userName);
    void initBrands();
    void updateBrand(BrandUpdateServiceModel brandModel) throws ObjectNotFoundException;
}
