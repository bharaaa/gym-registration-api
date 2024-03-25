package com.enigma.gymregistration.service;

import com.enigma.gymregistration.model.request.UserRequest;
import com.enigma.gymregistration.model.response.UserResponse;

import java.util.List;

public interface UserService {
    UserResponse findUserById(String id);
    List<UserResponse> findAllUser();
    UserResponse updateUser(UserRequest request);
}
