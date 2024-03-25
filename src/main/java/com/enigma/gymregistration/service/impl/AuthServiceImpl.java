package com.enigma.gymregistration.service.impl;

import com.enigma.gymregistration.constant.ERole;
import com.enigma.gymregistration.constant.MemberStatus;
import com.enigma.gymregistration.model.entity.Role;
import com.enigma.gymregistration.model.request.LoginRequest;
import com.enigma.gymregistration.model.request.RegisterRequest;
import com.enigma.gymregistration.model.response.LoginResponse;
import com.enigma.gymregistration.model.response.RegisterResponse;
import com.enigma.gymregistration.repository.UserRepository;
import com.enigma.gymregistration.service.AuthService;
import com.enigma.gymregistration.service.RoleService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final RoleService roleService;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public RegisterResponse registerAdmin(RegisterRequest request) {
        Role role = roleService.getOrSaveRole(ERole.ADMIN);

        userRepository.saveUser(
                UUID.randomUUID().toString(),
                request.getUserName(),
                request.getEmail(),
                request.getPassword(),
                role.getId(),
                MemberStatus.ACTIVE
                );

        return RegisterResponse.builder()
                .userName(request.getUserName())
                .email(request.getEmail())
                .role(String.valueOf(role.getRole()))
                .build();
    }

    @Override
    @Transactional
    public RegisterResponse registerTrainer(RegisterRequest request) {
        Role role = roleService.getOrSaveRole(ERole.TRAINER);

        userRepository.saveUser(
                UUID.randomUUID().toString(),
                request.getUserName(),
                request.getEmail(),
                request.getPassword(),
                role.getId(),
                MemberStatus.ACTIVE
        );

        return RegisterResponse.builder()
                .userName(request.getUserName())
                .email(request.getEmail())
                .role(String.valueOf(role.getRole()))
                .build();
    }

    @Override
    @Transactional
    public RegisterResponse registerMember(RegisterRequest request) {
        Role role = roleService.getOrSaveRole(ERole.MEMBER);

        userRepository.saveUser(
                UUID.randomUUID().toString(),
                request.getUserName(),
                request.getEmail(),
                request.getPassword(),
                role.getId(),
                MemberStatus.ACTIVE
        );

        return RegisterResponse.builder()
                .userName(request.getUserName())
                .email(request.getEmail())
                .role(String.valueOf(role.getRole()))
                .build();
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        return null;
    }
}
