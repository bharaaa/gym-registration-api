package com.enigma.gymregistration.service;

import com.enigma.gymregistration.model.request.ClassRegistrationRequest;
import com.enigma.gymregistration.model.request.GymClassRequest;
import com.enigma.gymregistration.model.response.ClassRegistrationResponse;
import com.enigma.gymregistration.model.response.GymClassResponse;

import java.util.List;

public interface ClassRegistrationService {
    ClassRegistrationResponse registerClass(ClassRegistrationRequest request);
    ClassRegistrationResponse findRegistrationById(String id);
    List<ClassRegistrationResponse> findAllRegistration();
    ClassRegistrationResponse updateClass(ClassRegistrationResponse request);
}
