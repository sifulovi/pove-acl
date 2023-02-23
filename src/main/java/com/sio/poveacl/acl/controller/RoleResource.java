package com.sio.poveacl.acl.controller;

import com.sio.poveacl.acl.dto.RoleDTO;
import com.sio.poveacl.acl.service.RoleService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/designations", produces = MediaType.APPLICATION_JSON_VALUE)
public class RoleResource {

    private final RoleService roleService;

    @GetMapping
    public ResponseEntity<List<RoleDTO>> getAllDesignations() {
        return ResponseEntity.ok(roleService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleDTO> getDesignation(@PathVariable(name = "id") final Long id) {
        return ResponseEntity.ok(roleService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createDesignation(
            @RequestBody @Valid final RoleDTO roleDTO) {
        return new ResponseEntity<>(roleService.create(roleDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateDesignation(@PathVariable(name = "id") final Long id,
                                                  @RequestBody @Valid final RoleDTO roleDTO) {
        roleService.update(id, roleDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<?> deleteDesignation(@PathVariable(name = "id") final Long id) {
        final String referencedWarning = roleService.getReferencedWarning(id);
        if (referencedWarning != null) {
            return new ResponseEntity<>(referencedWarning, HttpStatus.BAD_REQUEST);
        }
        roleService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
