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
public class ClassRegistrationResponse {
    private String id;
    private User userId;
    private GymClass gymClassId;
    private Date registrationDate;
}
