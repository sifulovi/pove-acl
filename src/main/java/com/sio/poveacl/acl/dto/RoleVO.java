package com.sio.poveacl.acl.dto;


import com.sio.poveacl.acl.domain.PoveStatus;

import java.util.List;

public record RoleVO(Long id, String title, String name, PoveStatus status, List<Scope> scopes) {
}
