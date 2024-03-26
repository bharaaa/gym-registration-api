package com.enigma.gymregistration.service;

import com.enigma.gymregistration.model.entity.User;
import com.enigma.gymregistration.model.request.UserRequest;
import com.enigma.gymregistration.model.response.UserResponse;

import java.util.List;

public interface TrainerService {
    UserResponse findUserById(String id);
    List<UserResponse> findAllUser();
    UserResponse updateUser(UserRequest request);
    User loadUserByUserId(String id);
}
