package com.enigma.gymregistration.service;

import com.enigma.gymregistration.model.request.GymClassRequest;
import com.enigma.gymregistration.model.response.GymClassResponse;

import java.util.List;

public interface GymClassService {
    GymClassResponse registerClass(GymClassRequest request);
    GymClassResponse findClassById(String id);
    List<GymClassResponse> findAllClass();
    GymClassResponse updateClass(GymClassRequest request);
}
