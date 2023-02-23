package com.sio.poveacl.acl.configuration;


import com.sio.poveacl.acl.domain.AppUser;
import com.sio.poveacl.acl.domain.Feature;
import com.sio.poveacl.acl.dto.Scope;
import com.sio.poveacl.acl.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthUserDetailsService implements UserDetailsService {

    private final UserRepository actorRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = actorRepository
                .findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        return User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities(getAllFeatures(user))
                .build();
    }

    String[] getAllFeatures(AppUser actor) {
        return actor.getRoles().stream()
                .flatMap(designation -> designation.getFeatures().stream())
                .map(Feature::getName)
                .toArray(String[]::new);
    }

    public List<Scope> getAllScopes(AppUser actor) {
        return actor.getRoles().stream()
                .flatMap(designation -> designation.getFeatures().stream())
                .map(it -> new Scope(it.getName(), it.getUrl()))
                .toList();
    }

}
