package com.enigma.gymregistration.service;

import com.enigma.gymregistration.model.request.GymClassRequest;
import com.enigma.gymregistration.model.response.AddClassResponse;
import com.enigma.gymregistration.model.response.GymClassResponse;

import java.util.List;

public interface GymClassService {
    AddClassResponse addClass(GymClassRequest request);
    GymClassResponse findClassById(String id);
    List<GymClassResponse> findAllClass();
    GymClassResponse updateClass(GymClassRequest request);
}
