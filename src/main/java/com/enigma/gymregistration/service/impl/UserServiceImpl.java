package com.enigma.gymregistration.service.impl;

import com.enigma.gymregistration.constant.ERole;
import com.enigma.gymregistration.constant.MemberStatus;
import com.enigma.gymregistration.model.entity.Role;
import com.enigma.gymregistration.model.entity.User;
import com.enigma.gymregistration.model.request.UserRequest;
import com.enigma.gymregistration.model.response.UserResponse;
import com.enigma.gymregistration.repository.UserRepository;
import com.enigma.gymregistration.service.RoleService;
import com.enigma.gymregistration.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.enigma.gymregistration.constant.MemberStatus.ACTIVE;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleService roleService;

    @Override
    public UserResponse findUserById(String id) {
        Optional<User> optionalUser = userRepository.findUserById(id);

        if (optionalUser.isPresent()){
            User user = optionalUser.get();

            return UserResponse.builder()
                    .id(user.getId())
                    .userName(user.getUserName())
                    .email(user.getEmail())
                    .role(user.getRole())
                    .memberStatus(user.getMemberStatus())
                    .build();
        }
        return null;
    }

    @Override
    public List<UserResponse> findAllUser() {
        return userRepository.findAll().stream()
                .map(user -> UserResponse.builder()
                        .id(user.getId())
                        .userName(user.getUserName())
                        .email(user.getEmail())
                        .role(user.getRole())
                        .memberStatus(user.getMemberStatus())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public UserResponse updateUser(UserRequest request) {
        UserResponse existingUser = findUserById(request.getId());

        if (existingUser != null){
            userRepository.saveUser(
                    existingUser.getId(),
                    request.getUserName(),
                    request.getEmail(),
                    request.getPassword(),
                    String.valueOf(existingUser.getRole()),
                    MemberStatus.valueOf(request.getMemberStatus())
            );

            return UserResponse.builder()
                    .id(existingUser.getId())
                    .userName(request.getUserName())
                    .email(request.getEmail())
                    .role(existingUser.getRole())
                    .memberStatus(MemberStatus.valueOf(request.getMemberStatus()))
                    .build();
        }
        return null;
    }
}
