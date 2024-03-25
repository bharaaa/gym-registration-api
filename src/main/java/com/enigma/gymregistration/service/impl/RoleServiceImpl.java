package com.enigma.gymregistration.service.impl;

import com.enigma.gymregistration.constant.ERole;
import com.enigma.gymregistration.model.entity.Role;
import com.enigma.gymregistration.repository.RoleRepository;
import com.enigma.gymregistration.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public Role getOrSaveRole(ERole role) {
        Optional<Role> optionalRole = roleRepository.findByRole(role);

        if (optionalRole.isPresent()) {
            return optionalRole.get();
        }

        Role currentRole = Role.builder()
                .role(role)
                .build();

        return roleRepository.saveAndFlush(currentRole);
    }
}
