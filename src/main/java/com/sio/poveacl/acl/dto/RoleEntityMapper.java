package com.sio.poveacl.acl.dto;

import com.sio.poveacl.acl.domain.Feature;
import com.sio.poveacl.acl.domain.Role;
import com.sio.poveacl.acl.exception.NotFoundException;
import com.sio.poveacl.acl.repository.FeatureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;

@Component
@RequiredArgsConstructor
public class RoleEntityMapper implements EntityMapper<RoleDTO, Role> {

    private final FeatureRepository featureRepository;

    @Override
    public Role convert(RoleDTO roleDTO, Role role) {
        role.setTitle(roleDTO.getTitle());
        role.setName(roleDTO.getName());
        role.setDescription(roleDTO.getDescription());
        role.setStatus(roleDTO.getStatus());
        final List<Feature> featuress = featureRepository.findAllById(
                roleDTO.getFeatures() == null ? Collections.emptyList() : roleDTO.getFeatures());
        if (featuress.size() != (roleDTO.getFeatures() == null ? 0 : roleDTO.getFeatures().size())) {
            throw new NotFoundException("one of featuress not found");
        }
        role.setFeatures(new HashSet<>(featuress));
        return role;
    }
}
