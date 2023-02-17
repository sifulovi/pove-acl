package com.sio.poveacl.acl.controller;

import com.sio.poveacl.acl.configuration.JwtService;
import com.sio.poveacl.acl.configuration.LoginRequest;
import com.sio.poveacl.acl.configuration.LoginResponse;
import com.sio.poveacl.acl.configuration.ResourceUrl;
import com.sio.poveacl.acl.domain.AppUser;
import com.sio.poveacl.acl.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor

public class TestController {

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;

    @GetMapping(ResourceUrl.USER_LIST)
    public String userList() {
        return "User List";
    }

    @GetMapping(ResourceUrl.CREATE_USER)
    public String createUser() {
        return "Create User";
    }


    @PostMapping("/auth/token")
    public ResponseEntity<?> authenticate(@RequestBody LoginRequest request) {

        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.username(), request.password()));
        if (authenticate.isAuthenticated()) {
            String token = jwtService.generateToken(request.username());
            AppUser authActor = userRepository.findUserByUsername(request.username()).orElseThrow(() -> new UsernameNotFoundException("User no found !"));
            var response = new LoginResponse(authActor.getId().toString(), authActor.getUsername(), authActor.getFullName(), token);
            return ResponseEntity.ok(response);
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

}
