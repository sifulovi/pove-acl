package com.sio.poveacl.acl.service;

import com.sio.poveacl.acl.domain.AppUser;
import com.sio.poveacl.acl.dto.*;
import com.sio.poveacl.acl.repository.RoleRepository;
import com.sio.poveacl.acl.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public List<UserDTO> list() {
        return userRepository.findAll().stream().map(new UserModelMapper()).toList();
    }

    public UserDTO create(UserRequestDTO userRequestDTO) {
        AppUser user = new UserEntityMapper().convert(userRequestDTO, new AppUser(), roleRepository);
        user = userRepository.save(user);
        return new UserDtoMapper().convert(user, null);
    }
}
