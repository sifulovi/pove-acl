package com.sio.poveacl.acl.dto;

public interface EntityMapperWithRepo<S, D, R> {

    D convert(S s, D d , R r);
}
