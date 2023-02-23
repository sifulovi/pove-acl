package com.sio.poveacl.acl.dto;

public interface EntityMapper<S, D> {

    D convert(S s, D d);
}
