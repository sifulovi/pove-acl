package com.sio.poveacl.acl.dto;

import com.sio.poveacl.acl.domain.PoveStatus;
import com.sio.poveacl.acl.domain.Role;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;

@Component
public class RoleVOMapper implements Function<Role, RoleVO> {
    @Override
    public RoleVO apply(Role role) {
        var scopes = role.getFeatures() == null ? null : role.getFeatures().stream()
                .map(it -> new Scope(it.getName(), it.getUrl()))
                .toList();
        return new RoleVO(role.getId(), role.getTitle(), role.getName(), role.getStatus(), scopes);
    }
}
