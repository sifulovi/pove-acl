package com.sio.poveacl.acl.configuration;

import com.sio.poveacl.acl.dto.Scope;

import java.util.List;

public record LoginResponseWithScopes(String token ,String uid, String username, String fullName, boolean hasGivenAccess,  List<Scope> scopes) {
}
