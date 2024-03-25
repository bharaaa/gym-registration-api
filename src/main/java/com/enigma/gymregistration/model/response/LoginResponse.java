package com.enigma.gymregistration.model.response;

import com.enigma.gymregistration.model.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
    private String id;
    private String userName;
    private String email;
    private Role role;
}
