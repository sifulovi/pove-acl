package com.sio.poveacl.acl.dto;

import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record UserRequestDTO(
        @NotBlank(message = "required")
        String fullName,
        @NotBlank(message = "required")
        String email,
        @NotBlank(message = "required")
        String username,
        String password,
        List<Long> roleIds
) {
}
