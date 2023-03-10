package com.sio.poveacl.acl.service;

import com.sio.poveacl.acl.domain.AppUser;
import com.sio.poveacl.acl.dto.*;
import com.sio.poveacl.acl.exception.NotFoundException;
import com.sio.poveacl.acl.repository.RoleRepository;
import com.sio.poveacl.acl.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public List<UserDTO> list() {
        return userRepository.findAll().stream().map(new UserModelMapper()).toList();
    }

    public UserDTO create(UserRequestDTO userRequestDTO) {
        AppUser user = new UserEntityMapper().convert(userRequestDTO, new AppUser(), roleRepository);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user = userRepository.save(user);
        return new UserDtoMapper().convert(user, null);
    }


    public UserDTO update(UserRequestDTO userRequestDTO, Long id) {
        AppUser appUser = userRepository.findById(id).orElseThrow(NotFoundException::new);
        AppUser user = new UserEntityMapper().convert(userRequestDTO, appUser, roleRepository);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user = userRepository.save(user);
        return new UserDtoMapper().convert(user, null);
    }


    public AppUserVO getUser(Long userId) {
        AppUser appUser = userRepository.findById(userId).orElseThrow(NotFoundException::new);
        var roles = appUser.getRoles().stream().map(role -> new AppUserVO.RoleAppVO(role.getId(), role.getName())).toList();

        return new AppUserVO(appUser.getId(),
                appUser.getFullName(),
                appUser.getEmail(),
                appUser.getUsername(),
                roles
        );
    }
}
