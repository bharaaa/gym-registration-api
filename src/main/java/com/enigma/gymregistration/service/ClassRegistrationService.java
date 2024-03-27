package com.enigma.gymregistration.service;

import com.enigma.gymregistration.model.request.ClassRegistrationRequest;
import com.enigma.gymregistration.model.request.GymClassRequest;
import com.enigma.gymregistration.model.response.ClassRegistrationResponse;
import com.enigma.gymregistration.model.response.GymClassResponse;
import com.enigma.gymregistration.model.response.UpdateRegistrationResponse;

import java.util.List;

public interface ClassRegistrationService {
    UpdateRegistrationResponse registerClass(ClassRegistrationRequest request);
    ClassRegistrationResponse findRegistrationById(String id);
    List<ClassRegistrationResponse> findAllRegistration();
    UpdateRegistrationResponse updateRegistration(ClassRegistrationRequest request);
    ClassRegistrationResponse deleteRegistration(String id);
}
