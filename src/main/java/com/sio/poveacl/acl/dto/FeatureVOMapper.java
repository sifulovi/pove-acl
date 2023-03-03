package com.sio.poveacl.acl.dto;

import com.sio.poveacl.acl.domain.Feature;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class FeatureVOMapper implements Function<Feature, FeatureVO> {
    @Override
    public FeatureVO apply(Feature feature) {
        return new FeatureVO(feature.getId(), feature.getName(), feature.getUrl(), feature.getDescription());
    }
}