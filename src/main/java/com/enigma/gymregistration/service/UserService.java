package com.enigma.gymregistration.service;

import com.enigma.gymregistration.model.entity.AppUser;
import com.enigma.gymregistration.model.entity.User;
import com.enigma.gymregistration.model.request.UserRequest;
import com.enigma.gymregistration.model.response.UserResponse;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    UserResponse findUserById(String id);
    List<UserResponse> findAllUser();
    UserResponse updateUser(UserRequest request);
    UserResponse deleteUser(String id);
    AppUser loadUserByUserId(String id);
}
