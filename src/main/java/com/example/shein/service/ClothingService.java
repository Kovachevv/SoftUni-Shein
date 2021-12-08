package com.example.shein.service;

import com.example.shein.model.service.ClothesService.ClothesAddServiceModel;
import com.example.shein.model.service.ClothesService.ClothesUpdateServiceModel;
import com.example.shein.model.view.ClothingDetailsView;
import com.example.shein.model.view.ClothingSummaryView;
import javassist.tools.rmi.ObjectNotFoundException;

import java.util.*;

public interface ClothingService {

    List<ClothingSummaryView> getAllClothes();
    ClothingDetailsView findById(Long id,String username);
    void initClothes();
    void addClothing(ClothesAddServiceModel serviceModel);
    void deleteClothing(Long id);
    void updateClothing(ClothesUpdateServiceModel serviceModel) throws ObjectNotFoundException;

}
