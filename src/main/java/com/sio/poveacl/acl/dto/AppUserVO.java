package com.sio.poveacl.acl.dto;

import java.util.List;

public record AppUserVO(Long id,
                        String fullName,
                        String email,
                        String username,
                        List<RoleAppVO> roles) {
    public record RoleAppVO(Long id, String name) {
    }
}

