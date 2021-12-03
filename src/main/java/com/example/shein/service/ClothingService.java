package com.example.shein.service;

import com.example.shein.model.view.ClothingDetailsView;
import com.example.shein.model.view.ClothingSummaryView;

import java.util.*;

public interface ClothingService {

    List<ClothingSummaryView> getAllClothes();
    ClothingDetailsView findById(Long id);

}
