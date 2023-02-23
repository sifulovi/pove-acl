package com.sio.poveacl.acl.dto;

import com.sio.poveacl.acl.domain.AppUser;
import com.sio.poveacl.acl.domain.Role;
import com.sio.poveacl.acl.exception.NotFoundException;
import com.sio.poveacl.acl.repository.RoleRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;

@Component
public class UserEntityMapper implements EntityMapperWithRepo<UserRequestDTO, AppUser, RoleRepository> {

    @Override
    public AppUser convert(UserRequestDTO userRequestDTO, AppUser user, RoleRepository roleRepository) {
        BeanUtils.copyProperties(userRequestDTO, user);
        final List<Role> roles = roleRepository.findAllById(
                userRequestDTO.roleIds() == null ? Collections.emptyList() : userRequestDTO.roleIds());
        if (roles.size() != (userRequestDTO.roleIds() == null ? 0 : userRequestDTO.roleIds().size())) {
            throw new NotFoundException("one of roles not found");
        }
        user.setRoles(new HashSet<>(roles));
        return user;
    }
}
