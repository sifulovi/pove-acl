package com.sio.poveacl.acl.service;

import com.sio.poveacl.acl.dto.UserDTO;
import com.sio.poveacl.acl.dto.UserModelMapper;
import com.sio.poveacl.acl.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<UserDTO> list() {
        return userRepository.findAll().stream().map(new UserModelMapper()).toList();
    }
}
