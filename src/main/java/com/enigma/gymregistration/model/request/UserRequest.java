package com.enigma.gymregistration.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    private String id;
    private String userName;
    private String email;
    private String password;
    private String memberStatus;
}
