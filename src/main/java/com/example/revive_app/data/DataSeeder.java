package com.example.revive_app.data;

import java.util.List;
import java.util.Set;
import com.example.revive_app.model.Permission;
import com.example.revive_app.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import com.example.revive_app.repository.PermissionRepository;
import com.example.revive_app.repository.RoleRepository;

@Component
public class DataSeeder implements ApplicationRunner {

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void run(ApplicationArguments args) {
        if (permissionRepository.count() == 0 && roleRepository.count() == 0) {

            // Criar permissões
            Permission createUser = new Permission();
            createUser.setName("CREATE_USER");
            createUser.setDescription("Allows creating a user");

            Permission deleteUser = new Permission();
            deleteUser.setName("DELETE_USER");
            deleteUser.setDescription("Allows deleting a user");

            Permission viewUser = new Permission();
            viewUser.setName("VIEW_USER");
            viewUser.setDescription("Allows viewing a user");

            permissionRepository.saveAll(List.of(createUser, deleteUser, viewUser));

            // Criar roles
            Role admin = new Role();
            admin.setName("ADMIN");
            admin.setDescription("Administrator with all permissions");
            admin.setPermissions(Set.of(createUser, deleteUser, viewUser));

            Role moderator = new Role();
            moderator.setName("MODERATOR");
            moderator.setDescription("Moderator with limited permissions");
            moderator.setPermissions(Set.of(viewUser));

            Role user = new Role();
            user.setName("USER");
            user.setDescription("Regular user with view-only permissions");
            user.setPermissions(Set.of(viewUser));

            roleRepository.saveAll(List.of(admin, moderator, user));
        }
    }
}

