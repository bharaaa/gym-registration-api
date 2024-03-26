package com.enigma.gymregistration.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class ClassRegistrationRequest {
    private String id;
    private String userId;
    private String gymClassId;
    private String registrationDate;
}
