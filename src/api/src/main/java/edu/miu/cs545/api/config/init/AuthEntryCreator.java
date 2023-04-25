package edu.miu.cs545.api.config.init;

import edu.miu.cs545.api.entity.Role;
import edu.miu.cs545.api.entity.RoleTypes;
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
        // This creates the roles and the admin user with all roles.
        // Also encrypts the passwords
        // Ideally the usernames and passwords should be set using the combination of EL and env variables
        List<Role> roles = new ArrayList<>();
        Role role = roleRepository.findById(RoleTypes.ADMIN.toString()).orElse(null);
        if (role == null) {
            role = new Role();
            role.setRole(RoleTypes.ADMIN.toString());
            role.setDescription("Admin privileges");
            role = roleRepository.save(role);
        }
        roles.add(role);

        role = roleRepository.findById(RoleTypes.CUSTOMER.toString()).orElse(null);
        if (role == null) {
            role = new Role();
            role.setRole(RoleTypes.CUSTOMER.toString());
            role.setDescription("Customer privileges");
            role = roleRepository.save(role);
        }
        roles.add(role);

        role = roleRepository.findById(RoleTypes.OWNER.toString()).orElse(null);
        if (role == null) {
            role = new Role();
            role.setRole(RoleTypes.OWNER.toString());
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

            List<User> users = new ArrayList<>();
            users.add(existingUser);
            for(Role savedRole : roles){
                savedRole.setUsers(users);
                roleRepository.save(savedRole);
            }
        }
    }

}