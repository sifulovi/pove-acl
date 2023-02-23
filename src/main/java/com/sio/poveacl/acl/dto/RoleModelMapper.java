package com.sio.poveacl.acl.dto;

import com.sio.poveacl.acl.domain.Base;
import com.sio.poveacl.acl.domain.Role;
import org.springframework.stereotype.Component;

import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class RoleModelMapper implements Function<Role, RoleDTO> {
    @Override
    public RoleDTO apply(Role role) {
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setId(role.getId());
        roleDTO.setTitle(role.getTitle());
        roleDTO.setName(role.getName());
        roleDTO.setDescription(role.getDescription());
        roleDTO.setStatus(role.getStatus());
        roleDTO.setFeatures(role.getFeatures() == null ? null : role.getFeatures().stream()
                .map(Base::getId)
                .collect(Collectors.toList()));

        return roleDTO;
    }
}
