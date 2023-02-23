package com.sio.poveacl.acl.dto;

import com.sio.poveacl.acl.domain.AppUser;
import com.sio.poveacl.acl.domain.Role;
import org.springframework.stereotype.Component;

@Component
public class UserDtoMapper implements EntityMapper<AppUser, UserDTO> {

    @Override
    public UserDTO convert(AppUser user, UserDTO userDTO) {
        String[] scops = user.getRoles() == null ? null : user.getRoles().stream()
                .map(Role::getName)
                .toArray(String[]::new);

        return new UserDTO(user.getUsername(), user.getFullName(), scops);
    }
}
