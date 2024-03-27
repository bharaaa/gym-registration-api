package com.enigma.gymregistration.controller;

import com.enigma.gymregistration.model.request.ClassRegistrationRequest;
import com.enigma.gymregistration.model.response.*;
import com.enigma.gymregistration.service.ClassRegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/class-registration")
@RequiredArgsConstructor
public class ClassRegistrationController {
    private final ClassRegistrationService classRegistrationService;

    @PostMapping("/register")
    public ResponseEntity<?> registerClass(@RequestBody ClassRegistrationRequest request) {
        UpdateRegistrationResponse classRegistrationResponse = classRegistrationService.registerClass(request);

        CommonResponse<UpdateRegistrationResponse> response = CommonResponse.<UpdateRegistrationResponse>builder()
                .message("Successfully register class")
                .statusCode(HttpStatus.CREATED.value())
                .data(classRegistrationResponse)
                .build();

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getRegistrationById(@PathVariable String id){
        ClassRegistrationResponse classRegistrationResponse = classRegistrationService.findRegistrationById(id);
        CommonResponse<ClassRegistrationResponse> response = CommonResponse.<ClassRegistrationResponse>builder()
                .message("Successfully retrieved registration")
                .statusCode(HttpStatus.OK.value())
                .data(classRegistrationResponse)
                .build();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<?> getAllRegistration(){
        List<ClassRegistrationResponse> classRegistrationResponses = classRegistrationService.findAllRegistration();
        CommonResponse<List<ClassRegistrationResponse>> response = CommonResponse.<List<ClassRegistrationResponse>>builder()
                .message("Successfully retrieved all registration")
                .statusCode(HttpStatus.OK.value())
                .data(classRegistrationResponses)
                .build();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @PutMapping
    public ResponseEntity<?> updateRegistration(@RequestBody ClassRegistrationRequest request) {
        UpdateRegistrationResponse classRegistrationResponse = classRegistrationService.updateRegistration(request);

        CommonResponse<UpdateRegistrationResponse> response = CommonResponse.<UpdateRegistrationResponse>builder()
                .message("Successfully update registration")
                .statusCode(HttpStatus.OK.value())
                .data(classRegistrationResponse)
                .build();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }
}
