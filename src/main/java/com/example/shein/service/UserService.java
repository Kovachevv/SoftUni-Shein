package com.example.shein.service;

import com.example.shein.model.binding.UserRegisterBindingModel;
import com.example.shein.model.service.UserRegisterServiceModel;

public interface UserService {

    void initUsersAndRoles();

    void registerAndLoginUser(UserRegisterServiceModel serviceModel);
}
