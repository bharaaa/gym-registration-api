package com.enigma.gymregistration.service.impl;

import com.enigma.gymregistration.constant.MemberStatus;
import com.enigma.gymregistration.model.entity.AppUser;
import com.enigma.gymregistration.model.entity.User;
import com.enigma.gymregistration.model.request.UserRequest;
import com.enigma.gymregistration.model.response.UserResponse;
import com.enigma.gymregistration.repository.UserRepository;
import com.enigma.gymregistration.service.RoleService;
import com.enigma.gymregistration.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleService roleService;

    @Override
    @Transactional(rollbackOn = Exception.class)
    public UserResponse findUserById(String id) {
        Optional<User> optionalUser = userRepository.findUserById(id);

        if (optionalUser.isPresent()){
            User user = optionalUser.get();

            return UserResponse.builder()
                    .id(user.getId())
                    .name(user.getName())
                    .email(user.getEmail())
                    .role(user.getRole())
                    .memberStatus(user.getMemberStatus())
                    .build();
        }
        return null;
    }

    @Override
    public List<UserResponse> findAllUser() {
        return userRepository.findAllUser().stream()
                .map(user -> UserResponse.builder()
                        .id(user.getId())
                        .name(user.getName())
                        .email(user.getEmail())
                        .role(user.getRole())
                        .memberStatus(user.getMemberStatus())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public UserResponse updateUser(UserRequest request) {
        UserResponse existingUser = findUserById(request.getId());

        userRepository.updateUser(
                request.getName(),
                request.getEmail(),
                request.getPassword(),
                existingUser.getRole().getId(),
                request.getMemberStatus(),
                existingUser.getId()
        );

        return UserResponse.builder()
                .id(existingUser.getId())
                .name(request.getName())
                .email(request.getEmail())
                .role(existingUser.getRole())
                .memberStatus(MemberStatus.valueOf(request.getMemberStatus()))
                .build();
    }

    @Override
    public UserResponse deleteUser(String id) {
        Optional<User> optionalUser = userRepository.findUserById(id);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setMemberStatus(MemberStatus.INACTIVE); //soft delete
            userRepository.saveAndFlush(user);

            UserResponse.builder()
                    .id(user.getId())
                    .name(user.getName())
                    .email(user.getEmail())
                    .role(user.getRole())
                    .memberStatus(user.getMemberStatus())
                    .build();
        }
        return null;
    }

    @Override
    public AppUser loadUserByUserId(String id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("email not found"));

        return AppUser.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .password(user.getPassword())
                .role(user.getRole().getRole())
                .build();
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("invalid credential"));

        return AppUser.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .password(user.getPassword())
                .role(user.getRole().getRole())
                .build();
    }
}
