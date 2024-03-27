package com.enigma.gymregistration.model.response;

import com.enigma.gymregistration.model.entity.GymClass;
import com.enigma.gymregistration.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRegistrationResponse {
    private String userId;
    private String gymClassId;
    private Date registrationDate;
}
