package com.enigma.gymregistration.repository;

import com.enigma.gymregistration.constant.ERole;
import com.enigma.gymregistration.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
    Optional<Role> findByRole(ERole name);

}
