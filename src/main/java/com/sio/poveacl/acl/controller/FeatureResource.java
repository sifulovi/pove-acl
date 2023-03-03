package com.sio.poveacl.acl.controller;


import com.sio.poveacl.acl.dto.FeatureVO;
import com.sio.poveacl.acl.service.FeatureService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/features", produces = MediaType.APPLICATION_JSON_VALUE)
public class FeatureResource {

    private final FeatureService featureService;

    @GetMapping
    public ResponseEntity<List<FeatureVO>> getAllDesignations() {
        return ResponseEntity.ok(featureService.findAll());
    }

}
