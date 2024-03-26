package com.enigma.gymregistration.controller;

import com.enigma.gymregistration.model.request.ClassRegistrationRequest;
import com.enigma.gymregistration.model.request.GymClassRequest;
import com.enigma.gymregistration.model.response.ClassRegisterResponse;
import com.enigma.gymregistration.model.response.ClassRegistrationResponse;
import com.enigma.gymregistration.model.response.CommonResponse;
import com.enigma.gymregistration.model.response.GymClassResponse;
import com.enigma.gymregistration.service.ClassRegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/class-registration")
@RequiredArgsConstructor
public class ClassRegistrationController {
    private final ClassRegistrationService classRegistrationService;

    @PostMapping("/register")
    public ResponseEntity<?> registerClass(@RequestBody ClassRegistrationRequest request) {
        ClassRegistrationResponse classRegistrationResponse = classRegistrationService.registerClass(request);

        CommonResponse<ClassRegistrationResponse> response = CommonResponse.<ClassRegistrationResponse>builder()
                .message("Successfully register class")
                .statusCode(HttpStatus.CREATED.value())
                .data(classRegistrationResponse)
                .build();

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }
}
