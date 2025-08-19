package com.example.revive_app.data;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.revive_app.model.Address;
import com.example.revive_app.model.Employee;
import com.example.revive_app.model.Permission;
import com.example.revive_app.model.Role;
import com.example.revive_app.model.User;
import com.example.revive_app.repository.AddressRepository;
import com.example.revive_app.repository.EmployeeRepository;
import com.example.revive_app.repository.PermissionRepository;
import com.example.revive_app.repository.RoleRepository;
import com.example.revive_app.repository.UserRepository;

@Component
public class DataSeeder implements ApplicationRunner {

  @Autowired private PermissionRepository permissionRepository;

  @Autowired private RoleRepository roleRepository;

  @Autowired private UserRepository userRepository;

  @Autowired private EmployeeRepository employeeRepository;

  @Autowired private AddressRepository addressRepository;

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

    Address address = new Address();
    address.setStreet("123 Main St");
    address.setCity("Luanda");
    address.setState("Luanda Province");
    address.setZipCode("1000");
    address.setEmployee(empUser);

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

      Permission readDepartment = new Permission();
      readDepartment.setName(Permissions.READ_DEPARTMENT);
      readDepartment.setDescription("Allows user read a department");


      Permission readAddress = new Permission();
      readAddress.setName(Permissions.READ_ADDRESS);
      readAddress.setDescription("Allows user read address");

      Permission readAddresses = new Permission();
      readAddresses.setName(Permissions.READ_ADDRESSES);
      readAddresses.setDescription("Allows user read addresses");

      Permission createAddress = new Permission();
      createAddress.setName(Permissions.CREATE_ADDRESS);
      createAddress.setDescription("Allows create address");

      Permission createAddresses = new Permission();
      createAddresses.setName(Permissions.CREATE_ADDRESSES);
      createAddresses.setDescription("Allows create addresses");

      Permission updateAddress = new Permission();
      updateAddress.setName(Permissions.UPDATE_ADDRESS);
      updateAddress.setDescription("Allows user to update department");

      Permission updateAddresses = new Permission();
      updateAddresses.setName(Permissions.UPDATE_ADDRESSES);
      updateAddresses.setDescription("Allows users to update addresses");

      Permission deleteAddress = new Permission();
      deleteAddress.setName(Permissions.DELETE_ADDRESS);
      deleteAddress.setDescription("Allows users to delete Address");


      Permission readDepartments = new Permission();
      readDepartments.setName(Permissions.READ_DEPARTMENTS);
      readDepartments.setDescription("Allows user read departments");

      Permission createDepartment = new Permission();
      createDepartment.setName(Permissions.CREATE_DEPARTMENT);
      createDepartment.setDescription("Allows create department");

      Permission createDepartments = new Permission();
      createDepartments.setName(Permissions.CREATE_DEPARTMENTS);
      createDepartments.setDescription("Allows create departments");

      Permission updateDepartment = new Permission();
      updateDepartment.setName(Permissions.UPDATE_DEPARTMENT);
      updateDepartment.setDescription("Allows user to update department");

      Permission updateDepartments = new Permission();
      updateDepartments.setName(Permissions.UPDATE_DEPARTMENTS);
      updateDepartments.setDescription("Allows users to update departments");

      Permission deleteDepartment = new Permission();
      deleteDepartment.setName(Permissions.DELETE_DEPARTMENT);
      deleteDepartment.setDescription("Allows users to delete department");

      permissionRepository.saveAll(
          List.of(
              createUsers,
              createUser,
              deleteUser,
              readUsers,
              readUser,
              readMe,

              readAddress,
              readAddresses,
              createAddress,
              createAddresses,
              updateAddress,
              updateAddresses,
              deleteAddress,

              readDepartment,
              readDepartments,
              createDepartment,
              createDepartments,
              updateDepartment,
              updateDepartments,
              deleteDepartment
              ));

      // Criar roles
      Role admin = new Role();
      admin.setName(Roles.ADMIN);
      admin.setDescription("Administrator with all permissions");
      admin.setPermissions(
          Set.of(
              createUsers,
              createUser,
              deleteUser,
              readUsers,
              readUser,
              readMe,
              
              readAddress,
              readAddresses,
              createAddress,
              createAddresses,
              updateAddress,
              updateAddresses,
              deleteAddress,

              readDepartment,
              readDepartments,
              createDepartment,
              createDepartments,
              updateDepartment,
              updateDepartments,
              deleteDepartment
              ));

      Role moderator = new Role();
      moderator.setName(Roles.MODERATOR);
      moderator.setDescription("Moderator with limited permissions");
      moderator.setPermissions(Set.of(readUser, readMe, readAddress, readDepartment));

      Role user = new Role();
      user.setName(Roles.USER);
      user.setDescription("Regular user with view-only permissions");
      user.setPermissions(Set.of(readMe, readAddress, readDepartment));

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
    addressRepository.save(address);
  }
}
