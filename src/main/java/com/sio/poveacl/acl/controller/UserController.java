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
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping(ResourceUrl.USER_LIST)
    @Operation(summary = "User List", description = "Token is required!", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<?> userList() {
        return new ResponseEntity<>(userService.list(), HttpStatus.OK);
    }

    @PostMapping(ResourceUrl.CREATE_USER_ENDPOINT)
    @Operation(summary = "Create User", description = "Token is required!", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<?> createUser(@RequestBody @Valid UserRequestDTO userRequestDTO) {
        return new ResponseEntity<>(userService.create(userRequestDTO), HttpStatus.CREATED);
    }

    @GetMapping(ResourceUrl.UPDATE_USER)
    @Operation(summary = "Get User", description = "Token is required!", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<?> updateUser(@PathVariable Long id) {
        return new ResponseEntity<>(userService.getUser(id), HttpStatus.OK);
    }

}
