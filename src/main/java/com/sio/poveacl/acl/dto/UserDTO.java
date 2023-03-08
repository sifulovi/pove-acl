package com.sio.poveacl.acl.dto;

public record UserDTO(Long id, String username, String fullName, String[] scopes) {
}
