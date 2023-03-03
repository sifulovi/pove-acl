package com.sio.poveacl.acl.service;

import com.sio.poveacl.acl.domain.Feature;
import com.sio.poveacl.acl.dto.FeatureVO;
import com.sio.poveacl.acl.dto.FeatureVOMapper;
import com.sio.poveacl.acl.repository.FeatureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FeatureService {

    private final FeatureRepository featureRepository;

    public List<FeatureVO> findAll() {
        final List<Feature> designations = featureRepository.findAll(Sort.by("id"));
        return designations.stream().map(new FeatureVOMapper()).toList();
    }

}
