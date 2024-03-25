package com.enigma.gymregistration.service;

import com.enigma.gymregistration.constant.ERole;
import com.enigma.gymregistration.model.entity.Role;

public interface RoleService {
    Role getOrSaveRole(ERole role);
}
