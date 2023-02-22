package com.sio.poveacl.acl.controller;

import com.sio.poveacl.acl.configuration.ResourceUrl;
import com.sio.poveacl.acl.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ScopeTestController {

    private final UserService userService;

    @GetMapping(ResourceUrl.USER_LIST)
    @Operation(summary = "User List", description = "Token is required!", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<?> userList() {
        return new ResponseEntity<>(userService.list(), HttpStatus.OK);
    }

    @GetMapping(ResourceUrl.CREATE_USER)
    @Operation(summary = "Create User", description = "Token is required!", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<?> createUser() {
        return new ResponseEntity<>("Create User in not implemented yet!", HttpStatus.OK);
    }


}
