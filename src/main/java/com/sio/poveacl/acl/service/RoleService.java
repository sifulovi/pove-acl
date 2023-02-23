package com.sio.poveacl.acl.service;

import com.sio.poveacl.acl.domain.Feature;
import com.sio.poveacl.acl.domain.Role;
import com.sio.poveacl.acl.dto.RoleDTO;
import com.sio.poveacl.acl.dto.RoleEntityMapper;
import com.sio.poveacl.acl.dto.RoleModelMapper;
import com.sio.poveacl.acl.exception.NotFoundException;
import com.sio.poveacl.acl.repository.FeatureRepository;
import com.sio.poveacl.acl.repository.RoleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;
    private final FeatureRepository featureRepository;

    public List<RoleDTO> findAll() {
        final List<Role> designations = roleRepository.findAll(Sort.by("id"));
        return designations.stream().map(new RoleModelMapper()).toList();
    }

    public RoleDTO get(final Long id) {
        return roleRepository.findById(id)
                .map(new RoleModelMapper())
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final RoleDTO roleDTO) {
        Role role = new RoleEntityMapper(featureRepository).apply(roleDTO);
        return roleRepository.save(role).getId();
    }

    public void update(final Long id, final RoleDTO roleDTO) {
        Role role = roleRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(roleDTO, role);
        roleRepository.save(role);
    }

    public void delete(final Long id) {
        roleRepository.deleteById(id);
    }

    public boolean nameExists(final String name) {
        return roleRepository.existsByNameIgnoreCase(name);
    }

    @Transactional
    public String getReferencedWarning(final Long id) {
        final Role role = roleRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        if (!role.getFeatures().isEmpty()) {
            return "designation.actor.manyToMany.referenced" + role.getFeatures().iterator().next().getId();
        }
        return null;
    }

    private Role mapToEntity(final RoleDTO roleDTO, final Role role) {
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
