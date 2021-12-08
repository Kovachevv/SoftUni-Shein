package com.example.shein.service;

import com.example.shein.model.service.ClothesService.ClothesUpdateServiceModel;
import com.example.shein.model.service.ShoeService.ShoeAddServiceModel;
import com.example.shein.model.service.ShoeService.ShoeUpdateServiceModel;
import com.example.shein.model.view.ClothingDetailsView;
import com.example.shein.model.view.ShoeDetailsView;
import com.example.shein.model.view.ShoeViewModel;
import javassist.tools.rmi.ObjectNotFoundException;

import java.util.*;

public interface ShoeService {
    List<ShoeViewModel> getAllShoes();
    ShoeDetailsView findById(Long id, String username);
    void initShoes();
    void addShoe(ShoeAddServiceModel serviceModel);
    void deleteShoe(Long id);
    void updateShoe(ShoeUpdateServiceModel serviceModel) throws ObjectNotFoundException;
}
