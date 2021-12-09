package com.example.shein.service;

import com.example.shein.model.service.AccessoryService.AccessoryAddServiceModel;
import com.example.shein.model.service.AccessoryService.AccessoryUpdateServiceModel;
import com.example.shein.model.view.AccessoryDetailsViewModel;
import com.example.shein.model.view.AccessoryViewModel;
import javassist.tools.rmi.ObjectNotFoundException;

import java.util.*;

public interface AccessoryService {


    List<AccessoryViewModel> getAllAccessories();
    void initAccessories();
    AccessoryDetailsViewModel findById(Long id, String username);
    void addAccessory(AccessoryAddServiceModel serviceModel);
    void deleteAccessory(Long id);
    void updateAccessory(AccessoryUpdateServiceModel serviceModel) throws ObjectNotFoundException;
}
