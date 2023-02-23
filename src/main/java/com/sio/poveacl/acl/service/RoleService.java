package com.sio.poveacl.acl.service;

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
