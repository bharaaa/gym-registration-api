package com.enigma.gymregistration.service;

import com.enigma.gymregistration.model.entity.User;
import com.enigma.gymregistration.model.request.TrainerRequest;
import com.enigma.gymregistration.model.request.UserRequest;
import com.enigma.gymregistration.model.response.TrainerResponse;
import com.enigma.gymregistration.model.response.UserResponse;

import java.util.List;

public interface TrainerService {
    TrainerResponse findTrainerById(String id);
    List<TrainerResponse> findAllTrainer();
    TrainerResponse updateTrainer(TrainerRequest request);
}
