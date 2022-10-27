package com.sam.service;

import com.sam.dto.UserLoginRequest;
import com.sam.dto.UserRegisterRequest;
import com.sam.model.User;

public interface UserService {
    Integer register(UserRegisterRequest userRegisterRequest);

    User getUserById(Integer userId);

    User login(UserLoginRequest userLoginRequest);
}
