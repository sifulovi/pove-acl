package com.sio.poveacl.acl.seed;

import com.sio.poveacl.acl.configuration.ResourceUrl;
import com.sio.poveacl.acl.domain.AppUser;
import com.sio.poveacl.acl.domain.Feature;
import com.sio.poveacl.acl.domain.Role;
import com.sio.poveacl.acl.repository.FeatureRepository;
import com.sio.poveacl.acl.repository.RoleRepository;
import com.sio.poveacl.acl.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.List;

import static com.sio.poveacl.acl.configuration.ResourceUrl.CREATE_USER_RECORD;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final FeatureRepository featureRepository;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

        userRepository.deleteAll();
        roleRepository.deleteAll();
        featureRepository.deleteAll();


        List<Feature> list = ResourceUrl.getUserFeatureList().stream().map(item -> {
            Feature createUserFeature = new Feature();
            createUserFeature.setName(item.name());
            createUserFeature.setDescription(item.name());
            createUserFeature.setUrl(item.url());
            return createUserFeature;

        }).toList();

        featureRepository.saveAll(list);


        AppUser adminUser = new AppUser();
        adminUser.setUsername("admin");
        adminUser.setEmail("admin@gmail.com");
        adminUser.setPassword(passwordEncoder.encode("1234"));
        adminUser.setFullName("Admin");
        adminUser.setDateCreated(OffsetDateTime.now());
        adminUser = userRepository.save(adminUser);

        AppUser staffUser = new AppUser();
        staffUser.setUsername("staff");
        staffUser.setEmail("staff@gmail.com");
        staffUser.setPassword(passwordEncoder.encode("1234"));
        staffUser.setFullName("Staff");
        staffUser.setDateCreated(OffsetDateTime.now());
        staffUser = userRepository.save(staffUser);

        Role adminRole = new Role();
        adminRole.setName("Super Admin");
        adminRole.setDescription("Super Admin role");
        adminRole.setTitle("SUPER ADMIN FOR APPLICATION");
        adminRole = roleRepository.save(adminRole);

        Role staffRole = new Role();
        staffRole.setName("Staff User");
        staffRole.setDescription(" Staff role");
        staffRole.setTitle(" Staff FOR APPLICATION");
        staffRole = roleRepository.save(staffRole);

        adminRole.setFeatures(new HashSet<>(featureRepository.findAll()));
        adminRole = roleRepository.save(adminRole);
        adminUser.getRoles().add(adminRole);
        adminUser.setAccessGiven(true);
        userRepository.save(adminUser);

        staffRole = roleRepository.save(staffRole);
        staffUser.getRoles().add(staffRole);
        staffUser.setAccessGiven(true);

        userRepository.save(staffUser);


       /* userRepository.deleteAll();
        roleRepository.deleteAll();
        featureRepository.deleteAll();*/


    }
}
