package com.enigma.gymregistration.model.response;

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
    private String name;
    private String email;
    private String role;
    private String token;
}
