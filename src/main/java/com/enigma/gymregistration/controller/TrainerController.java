package com.enigma.gymregistration.controller;

import com.enigma.gymregistration.constant.AppPath;
import com.enigma.gymregistration.model.response.CommonResponse;
import com.enigma.gymregistration.model.response.TrainerResponse;
import com.enigma.gymregistration.service.TrainerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(AppPath.TRAINER)
@RequiredArgsConstructor
public class TrainerController {
    private final TrainerService trainerService;

    @GetMapping(AppPath.GET_BY_ID)
    public ResponseEntity<?> getTrainerById(@PathVariable String id){
        TrainerResponse trainerResponse = trainerService.findTrainerById(id);
        CommonResponse<TrainerResponse> response = CommonResponse.<TrainerResponse>builder()
                .message("Successfully retrieved trainer")
                .statusCode(HttpStatus.OK.value())
                .data(trainerResponse)
                .build();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<?> getAllTrainer(){
        List<TrainerResponse> trainerResponses = trainerService.findAllTrainer();
        CommonResponse<List<TrainerResponse>> response = CommonResponse.<List<TrainerResponse>>builder()
                .message("Successfully retrieved all user")
                .statusCode(HttpStatus.OK.value())
                .data(trainerResponses)
                .build();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

}
