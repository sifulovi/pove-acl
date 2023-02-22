package com.sio.poveacl.acl.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PublicController {

    @GetMapping("/api/public")
    public String index() {
        return "Welcome to the POVE ACL!";
    }
}
