package com.sio.poveacl.acl.dto;

import com.sio.poveacl.acl.domain.AppUser;
import com.sio.poveacl.acl.domain.Feature;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class UserModelMapper implements Function<AppUser, UserDTO> {
    @Override
    public UserDTO apply(AppUser appUser) {
        String[] scopes = appUser.getRoles().stream()
                .flatMap(designation -> designation.getFeatures().stream())
                .map(Feature::getName)
                .toArray(String[]::new);

        return new UserDTO(appUser.getUsername(), appUser.getFullName(), scopes);
    }
}
