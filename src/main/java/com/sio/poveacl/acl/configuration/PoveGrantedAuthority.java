package com.sio.poveacl.acl.configuration;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

@Data
class PoveGrantedAuthority implements GrantedAuthority {
    private String url;
    private String name;

    public PoveGrantedAuthority(String url, String name) {
        this.url = url;
        this.name = name;
    }

    @Override
    public String getAuthority() {
        return name;
    }
}