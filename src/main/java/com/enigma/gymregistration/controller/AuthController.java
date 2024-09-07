package com.enigma.gymregistration.controller;

import com.enigma.gymregistration.constant.AppPath;
import com.enigma.gymregistration.model.request.LoginRequest;
import com.enigma.gymregistration.model.request.RegisterRequest;
import com.enigma.gymregistration.model.response.CommonResponse;
import com.enigma.gymregistration.model.response.LoginResponse;
import com.enigma.gymregistration.model.response.RegisterResponse;
import com.enigma.gymregistration.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(AppPath.AUTHENTICATION)
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(AppPath.REGISTER_ADMIN)
    public ResponseEntity<?> registerAdmin(@RequestBody RegisterRequest request) {
        RegisterResponse registerResponse = authService.registerAdmin(request);

        CommonResponse<RegisterResponse> response = CommonResponse.<RegisterResponse>builder()
                .message("Successfully register admin")
                .statusCode(HttpStatus.CREATED.value())
                .data(registerResponse)
                .build();

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(AppPath.REGISTER_TRAINER)
    public ResponseEntity<?> registerTrainer(@RequestBody RegisterRequest request) {
        RegisterResponse registerResponse = authService.registerTrainer(request);

        CommonResponse<RegisterResponse> response = CommonResponse.<RegisterResponse>builder()
                .message("Successfully register trainer")
                .statusCode(HttpStatus.CREATED.value())
                .data(registerResponse)
                .build();

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(AppPath.REGISTER_MEMBER)
    public ResponseEntity<?> registerMember(@RequestBody RegisterRequest request) {
        RegisterResponse registerResponse = authService.registerMember(request);

        CommonResponse<RegisterResponse> response = CommonResponse.<RegisterResponse>builder()
                .message("Successfully register member")
                .statusCode(HttpStatus.CREATED.value())
                .data(registerResponse)
                .build();

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(AppPath.LOGIN)
    public ResponseEntity<?> login(@RequestBody LoginRequest request){
        LoginResponse loginResponse = authService.login(request);
        CommonResponse<LoginResponse> response = CommonResponse.<LoginResponse>builder()
                .message("Success login")
                .statusCode(HttpStatus.OK.value())
                .data(loginResponse)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
