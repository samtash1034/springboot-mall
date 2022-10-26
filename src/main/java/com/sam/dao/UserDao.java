package com.sam.dao;

import com.sam.dto.UserRegisterRequest;
import com.sam.model.User;

public interface UserDao {
    Integer createUser(UserRegisterRequest userRegisterRequest);

    User getUserById(Integer userId);

    User getUserByEmail(String email);
}
