package com.sio.poveacl.acl.configuration;

public record LoginResponse(String uid, String username, String fullName,boolean hasGivenAccess, String token) {
}
