package com.example.revive_app.data;

import com.example.revive_app.model.Employee;
import com.example.revive_app.model.Permission;
import com.example.revive_app.model.Role;
import com.example.revive_app.model.User;
import com.example.revive_app.repository.EmployeeRepository;
import com.example.revive_app.repository.PermissionRepository;
import com.example.revive_app.repository.RoleRepository;
import com.example.revive_app.repository.UserRepository;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder implements ApplicationRunner {

  @Autowired private PermissionRepository permissionRepository;

  @Autowired private RoleRepository roleRepository;

  @Autowired private UserRepository userRepository;

  @Autowired private EmployeeRepository employeeRepository;

  @Autowired private PasswordEncoder passwordEncoder;

  @Override
  public void run(ApplicationArguments args) {

    User adminUser = new User();
    adminUser.setUsername("admin");
    adminUser.setPassword(passwordEncoder.encode("admin123"));
    adminUser.setEmail("admins@system.com");

    Employee empUser = new Employee();
    empUser.setUsername("v.marcos");
    empUser.setPassword(passwordEncoder.encode("test_test"));
    empUser.setFirstname("Vanilson");
    empUser.setLastname("Marcos");
    empUser.setEmail("v.marcos@gmail.com");

    if (permissionRepository.count() == 0 && roleRepository.count() == 0) {
      // Criar permissões
      Permission createUser = new Permission();
      createUser.setName(Permissions.CREATE_USER);
      createUser.setDescription("Allows creating a user");

      Permission createUsers = new Permission();
      createUsers.setName(Permissions.CREATE_USERS);
      createUsers.setDescription("Allows creating multiple users");

      Permission deleteUser = new Permission();
      deleteUser.setName(Permissions.DELETE_USER);
      deleteUser.setDescription("Allows deleting a user");

      Permission readUser = new Permission();
      readUser.setName(Permissions.READ_USER);
      readUser.setDescription("Allows viewing a single user");

      Permission readUsers = new Permission();
      readUsers.setName(Permissions.READ_USERS);
      readUsers.setDescription("Allows viewing multiple users");

      Permission readMe = new Permission();
      readMe.setName(Permissions.READ_ME);
      readMe.setDescription("Allows viewing the authenticated user");

      permissionRepository.saveAll(
          List.of(createUsers, createUser, deleteUser, readUsers, readUser, readMe));

      // Criar roles
      Role admin = new Role();
      admin.setName(Roles.ADMIN);
      admin.setDescription("Administrator with all permissions");
      admin.setPermissions(
          Set.of(createUsers, createUser, deleteUser, readUsers, readUser, readMe));

      Role moderator = new Role();
      moderator.setName(Roles.MODERATOR);
      moderator.setDescription("Moderator with limited permissions");
      moderator.setPermissions(Set.of(readUser, readMe));

      Role user = new Role();
      user.setName(Roles.USER);
      user.setDescription("Regular user with view-only permissions");
      user.setPermissions(Set.of(readMe));

      roleRepository.saveAll(List.of(admin, moderator, user));

      empUser.setRoles(
          Set.of(
              roleRepository
                  .findByName(Roles.MODERATOR)
                  .orElseThrow(() -> new RuntimeException("Moderator role not found"))));
      adminUser.setRoles(
          Set.of(
              roleRepository
                  .findByName(Roles.ADMIN)
                  .orElseThrow(() -> new RuntimeException("Admin role not found"))));
    }

    userRepository.save(adminUser);
    employeeRepository.save(empUser);
  }
}
