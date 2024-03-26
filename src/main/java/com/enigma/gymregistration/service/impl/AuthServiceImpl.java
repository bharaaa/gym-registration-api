package com.enigma.gymregistration.service.impl;

import com.enigma.gymregistration.constant.ERole;
import com.enigma.gymregistration.constant.MemberStatus;
import com.enigma.gymregistration.model.entity.Role;
import com.enigma.gymregistration.model.entity.User;
import com.enigma.gymregistration.model.request.LoginRequest;
import com.enigma.gymregistration.model.request.RegisterRequest;
import com.enigma.gymregistration.model.response.LoginResponse;
import com.enigma.gymregistration.model.response.RegisterResponse;
import com.enigma.gymregistration.repository.TrainerRepository;
import com.enigma.gymregistration.repository.UserRepository;
import com.enigma.gymregistration.security.JwtUtil;
import com.enigma.gymregistration.service.AuthService;
import com.enigma.gymregistration.service.RoleService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final RoleService roleService;
    private final UserRepository userRepository;
    private final TrainerRepository trainerRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @Override
    @Transactional(rollbackOn = Exception.class)
    public RegisterResponse registerAdmin(RegisterRequest request) {
        Role role = roleService.getOrSaveRole(ERole.ADMIN);

        userRepository.saveUser(
                UUID.randomUUID().toString(),
                request.getName(),
                request.getEmail(),
                request.getPassword(),
                role.getId(),
                "ACTIVE"
                );

        return RegisterResponse.builder()
                .name(request.getName())
                .email(request.getEmail())
                .role(String.valueOf(role.getRole()))
                .build();
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public RegisterResponse registerTrainer(RegisterRequest request) {
        Role role = roleService.getOrSaveRole(ERole.TRAINER);

        userRepository.saveUser(
                UUID.randomUUID().toString(),
                request.getName(),
                request.getEmail(),
                request.getPassword(),
                role.getId(),
                "ACTIVE"
        );
        trainerRepository.saveTrainer(UUID.randomUUID().toString(), request.getName());

        return RegisterResponse.builder()
                .name(request.getName())
                .email(request.getEmail())
                .role(String.valueOf(role.getRole()))
                .build();
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public RegisterResponse registerMember(RegisterRequest request) {
        Role role = roleService.getOrSaveRole(ERole.MEMBER);

        userRepository.saveUser(
                UUID.randomUUID().toString(),
                request.getName(),
                request.getEmail(),
                request.getPassword(),
                role.getId(),
                "ACTIVE"
        );

        return RegisterResponse.builder()
                .name(request.getName())
                .email(request.getEmail())
                .role(String.valueOf(role.getRole()))
                .build();
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                ));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        User user = (User) authentication.getPrincipal();
        String token = jwtUtil.generateToken(user);

        return LoginResponse.builder()
                .id(user.getId())
                .name(user.getUsername())
                .token(token)
                .role(user.getRole().getRole().name())
                .build();
    }
}
