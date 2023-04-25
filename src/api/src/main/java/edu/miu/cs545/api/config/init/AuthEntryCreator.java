package edu.miu.cs545.api.config.init;

import edu.miu.cs545.api.entity.Role;
import edu.miu.cs545.api.entity.User;
import edu.miu.cs545.api.repository.RoleRepository;
import edu.miu.cs545.api.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class AuthEntryCreator {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostConstruct
    public void init() {
        List<Role> roles = new ArrayList<>();
        Role role = roleRepository.findById("Admin").orElse(null);
        if (role == null) {
            role = new Role();
            role.setRole("ADMIN");
            role.setDescription("Admin privileges");
            role = roleRepository.save(role);
        }
        roles.add(role);

        role = roleRepository.findById("CUSTOMER").orElse(null);
        if (role == null) {
            role = new Role();
            role.setRole("CUSTOMER");
            role.setDescription("Customer privileges");
            role = roleRepository.save(role);
        }
        roles.add(role);

        role = roleRepository.findById("OWNER").orElse(null);
        if (role == null) {
            role = new Role();
            role.setRole("OWNER");
            role.setDescription("Owner privileges");
            role = roleRepository.save(role);
        }
        roles.add(role);

        User existingUser = userRepository.findByEmail("admin@admin.com").orElse(null);
        if (existingUser != null) {
            existingUser.setPassword(bCryptPasswordEncoder.encode("admin"));
            userRepository.save(existingUser);
        }
        else {
            existingUser = new User();
            existingUser.setName("admin");
            existingUser.setEmail("admin@admin.com");
            existingUser.setPassword(bCryptPasswordEncoder.encode("admin"));
            userRepository.save(existingUser);
        }
        List<User> users = new ArrayList<>();
        users.add(existingUser);
        for(Role savedRole : roles){
            savedRole.setUsers(users);
            roleRepository.save(savedRole);
        }
    }

}