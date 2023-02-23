package com.sio.poveacl.acl.controller;

import com.sio.poveacl.acl.configuration.ResourceUrl;
import com.sio.poveacl.acl.dto.UserRequestDTO;
import com.sio.poveacl.acl.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping(ResourceUrl.CREATE_USER)
    @Operation(summary = "Create User", description = "Token is required!", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<?> createUser(@RequestBody @Valid UserRequestDTO userRequestDTO) {
        return new ResponseEntity<>(userService.create(userRequestDTO), HttpStatus.CREATED);
    }

}
