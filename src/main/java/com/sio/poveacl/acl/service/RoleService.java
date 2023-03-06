package com.sio.poveacl.acl.service;

import com.sio.poveacl.acl.domain.Role;
import com.sio.poveacl.acl.dto.*;
import com.sio.poveacl.acl.exception.NotFoundException;
import com.sio.poveacl.acl.repository.FeatureRepository;
import com.sio.poveacl.acl.repository.RoleRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;
    private final FeatureRepository featureRepository;

    public List<RoleVO> findAll() {
        final List<Role> designations = roleRepository.findAll(Sort.by("id"));
        return designations.stream().map(new RoleVOMapper()).toList();
    }

    public RoleVO get(final Long id) {
        return roleRepository.findById(id)
                .map(new RoleVOMapper())
                .orElseThrow(NotFoundException::new);
    }

    public Long create(@Valid final RoleDTO roleDTO) {
        Role role = new RoleEntityMapper(featureRepository).convert(roleDTO, new Role());
        return roleRepository.save(role).getId();
    }

    public void update(final Long id, final RoleDTO roleDTO) {
        Role role = roleRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        role = new RoleEntityMapper(featureRepository).convert(roleDTO, role);
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

}
