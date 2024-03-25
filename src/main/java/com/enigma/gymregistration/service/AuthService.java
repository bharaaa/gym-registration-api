package com.enigma.gymregistration.service;

import com.enigma.gymregistration.model.request.LoginRequest;
import com.enigma.gymregistration.model.request.RegisterRequest;
import com.enigma.gymregistration.model.response.LoginResponse;
import com.enigma.gymregistration.model.response.RegisterResponse;
import com.enigma.gymregistration.model.response.UserResponse;

public interface AuthService {
    RegisterResponse registerAdmin(RegisterRequest request);
    RegisterResponse registerTrainer(RegisterRequest request);
    RegisterResponse registerMember(RegisterRequest request);
    LoginResponse login(LoginRequest request);
}
