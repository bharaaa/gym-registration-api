package com.enigma.gymregistration.controller;

import com.enigma.gymregistration.constant.AppPath;
import com.enigma.gymregistration.model.request.UserRequest;
import com.enigma.gymregistration.model.response.CommonResponse;
import com.enigma.gymregistration.model.response.UserResponse;
import com.enigma.gymregistration.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(AppPath.USER)
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping(AppPath.GET_BY_ID)
    public ResponseEntity<?> getUserById(@PathVariable String id){
        UserResponse userResponse = userService.findUserById(id);
        CommonResponse<UserResponse> response = CommonResponse.<UserResponse>builder()
                .message("Successfully retrieved user")
                .statusCode(HttpStatus.OK.value())
                .data(userResponse)
                .build();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<?> getAllUser(){
        List<UserResponse> userResponses = userService.findAllUser();
        CommonResponse<List<UserResponse>> response = CommonResponse.<List<UserResponse>>builder()
                .message("Successfully retrieved all user")
                .statusCode(HttpStatus.OK.value())
                .data(userResponses)
                .build();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody UserRequest request) {
        UserResponse userResponse = userService.updateUser(request);
        CommonResponse<UserResponse> response = CommonResponse.<UserResponse>builder()
                .message("Successfully update user")
                .statusCode(HttpStatus.OK.value())
                .data(userResponse)
                .build();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @DeleteMapping(AppPath.GET_BY_ID)
    public ResponseEntity<?> deleteUserById(@PathVariable String id){
        UserResponse userResponse = userService.deleteUser(id);
        CommonResponse<UserResponse> response = CommonResponse.<UserResponse>builder()
                .message("Successfully delete user")
                .statusCode(HttpStatus.OK.value())
                .data(userResponse)
                .build();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

}
