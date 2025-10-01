package com.example.revive_app.data;

import com.example.revive_app.model.Address;
import com.example.revive_app.model.Admin;
import com.example.revive_app.model.Department;
import com.example.revive_app.model.Employee;
import com.example.revive_app.model.Exam;
import com.example.revive_app.model.ExamStatus;
import com.example.revive_app.model.Permission;
import com.example.revive_app.model.Question;
import com.example.revive_app.model.Role;
import com.example.revive_app.model.Subject;
import com.example.revive_app.model.User;
import com.example.revive_app.repository.AddressRepository;
import com.example.revive_app.repository.DepartmentRepository;
import com.example.revive_app.repository.EmployeeRepository;
import com.example.revive_app.repository.ExamRepository;
import com.example.revive_app.repository.ExamStatusRepository;
import com.example.revive_app.repository.PermissionRepository;
import com.example.revive_app.repository.QuestionRepository;
import com.example.revive_app.repository.RoleRepository;
import com.example.revive_app.repository.SubjectRepository;
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

  @Autowired private AddressRepository addressRepository;

  @Autowired private DepartmentRepository departmentRepository;

  @Autowired private PasswordEncoder passwordEncoder;

  @Autowired private ExamStatusRepository examStatusRepository;

  @Autowired private ExamRepository examRepository;

  @Autowired private QuestionRepository questionRepository;

  @Autowired private SubjectRepository subjectRepository;

  @Override
  public void run(ApplicationArguments args) {

    Department department = new Department();
    department.setName("Human Resources");
    department.setDescription("Handles employee relations and benefits");

    User adminUser = new Admin();
    adminUser.setUsername("admin");
    adminUser.setPassword(passwordEncoder.encode("admin123"));
    adminUser.setEmail("admins@system.com");

    Employee empOne = new Employee();
    empOne.setUsername("v.marcos");
    empOne.setPassword(passwordEncoder.encode("test_test"));
    empOne.setFirstname("Vanilson");
    empOne.setLastname("Marcos");
    empOne.setEmail("v.marcos@gmail.com");
    empOne.setDepartment(department);

    Employee empTwo = new Employee();
    empTwo.setUsername("j.silva");
    empTwo.setPassword(passwordEncoder.encode("test_two"));
    empTwo.setFirstname("João");
    empTwo.setLastname("Silva");
    empTwo.setEmail("j.silva@test.com");
    empTwo.setDepartment(department);

    // Set the head of the department
    department.setHead(empOne);
    department.setEmployees(List.of(empOne, empTwo));

    Address address = new Address();
    address.setStreet("123 Main St");
    address.setCity("Luanda");
    address.setState("Luanda Province");
    address.setZipCode("1000");
    address.setEmployee(empOne);

    Address addressTwo = new Address();
    addressTwo.setStreet("456 Elm St");
    addressTwo.setCity("Luanda");
    addressTwo.setState("Luanda Province");
    addressTwo.setZipCode("2000");
    addressTwo.setEmployee(empTwo);

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

      Permission readEmployee = new Permission();
      readEmployee.setName(Permissions.READ_EMPLOYEE);
      readEmployee.setDescription("Allows user read employees");

      Permission readEmployees = new Permission();
      readEmployees.setName(Permissions.READ_EMPLOYEES);
      readEmployees.setDescription("Allows user read employees");

      Permission createEmployee = new Permission();
      createEmployee.setName(Permissions.CREATE_EMPLOYEE);
      createEmployee.setDescription("Allows create employee");

      Permission createEmployees = new Permission();
      createEmployees.setName(Permissions.CREATE_EMPLOYEES);
      createEmployees.setDescription("Allows create employees");

      Permission updateEmployee = new Permission();
      updateEmployee.setName(Permissions.UPDATE_EMPLOYEE);
      updateEmployee.setDescription("Allows user to update employee");

      Permission updateEmployees = new Permission();
      updateEmployees.setName(Permissions.UPDATE_EMPLOYEES);
      updateEmployees.setDescription("Allows users to update employees");

      Permission deleteEmployee = new Permission();
      deleteEmployee.setName(Permissions.DELETE_EMPLOYEE);
      deleteEmployee.setDescription("Allows users to delete Employee");

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

      Permission readDepartment = new Permission();
      readDepartment.setName(Permissions.READ_DEPARTMENT);
      readDepartment.setDescription("Allows user read a department");

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

      Permission readExams = new Permission();
      readExams.setName(Permissions.READ_EXAMS);
      readExams.setDescription("Allows user read exams");

      permissionRepository.saveAll(
          List.of(
              createUsers,
              createUser,
              deleteUser,
              readUsers,
              readUser,
              readMe,
              readEmployee,
              readEmployees,
              createEmployee,
              createEmployees,
              updateEmployee,
              updateEmployees,
              deleteEmployee,
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
              deleteDepartment,
              readExams));

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
              readEmployee,
              readEmployees,
              createEmployee,
              createEmployees,
              updateEmployee,
              updateEmployees,
              deleteEmployee,
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
              deleteDepartment,
              readExams));

      Role moderator = new Role();
      moderator.setName(Roles.MODERATOR);
      moderator.setDescription("Moderator with limited permissions");
      moderator.setPermissions(Set.of(readUser, readMe, readAddress, readDepartment, readExams));

      Role user = new Role();
      user.setName(Roles.USER);
      user.setDescription("Regular user with view-only permissions");
      user.setPermissions(Set.of(readMe, readAddress, readDepartment, readExams));

      roleRepository.saveAll(List.of(admin, moderator, user));

      empOne.setRoles(
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

    ExamStatus readyStatus = new ExamStatus(null, "Ready", "Ready to take");
    ExamStatus inProgressStatus = new ExamStatus(null, "In Progress", "Currently taking the exam");
    ExamStatus completedStatus = new ExamStatus(null, "Completed", "Exam completed");
    ExamStatus reviewedStatus = new ExamStatus(null, "Reviewed", "Exam has been reviewed");

    // exams to insert
    List<Exam> exams =
        List.of(
            new Exam(null, "airlaw", "Air Law", "—", readyStatus),
            new Exam(null, "human-performance", "Human Performance", "—", readyStatus),
            new Exam(
                null, "aircraft-technical", "Aircraft Technical General", "—", inProgressStatus),
            new Exam(null, "flight-planning", "Flight Planning and Performance", "—", readyStatus),
            new Exam(null, "instruments", "Instruments and Electronics", "—", readyStatus),
            new Exam(null, "meteorology", "Meteorology", "—", readyStatus),
            new Exam(null, "general-navigation", "General Navigation", "—", completedStatus),
            new Exam(null, "radio-aids", "Radio Aids", "—", readyStatus));
    Subject instrumentRating =
        new Subject(null, "instrument Rating ", "Instruments and Electronics", "—", readyStatus);

    List<Subject> subjects =
        List.of(
            instrumentRating,
            new Subject(null, "airlaw", "Air Law", "—", readyStatus),
            new Subject(null, "human-performance", "Human Performance", "—", readyStatus),
            new Subject(
                null, "aircraft-technical", "Aircraft Technical General", "—", inProgressStatus),
            new Subject(
                null, "flight-planning", "Flight Planning and Performance", "—", readyStatus),
            new Subject(null, "meteorology", "Meteorology", "—", readyStatus),
            new Subject(null, "general-navigation", "General Navigation", "—", completedStatus),
            new Subject(null, "radio-aids", "Radio Aids", "—", readyStatus));

    List<Question> questions =
        List.of(
            new Question(
                null,
                "The minimum sector altitude (MSA) on an instrument apppraoch chart is referenced to a radio navigation facility, usually within… o 5 NM 30 NM",
                List.of("25 NM", "20 NM", "o Question 3 of 122", "ID:"),
                0,
                instrumentRating),
            new Question(
                null,
                "QNH 1025 HPa ISA +10 deg. C IFR flight 135° magnetic course airway MSA 7800 ft o The minimum flight level is: o FL75 FL65",
                List.of("FL90", "FL80", "o Question 4 of 122", "ID:"),
                0,
                instrumentRating),
            new Question(
                null,
                "Excluding RVSM, an appropriate flight level (FL) for an IFR flight in accordance with the semi-circular height rules on a Magnetic Course 200 is: o FL320 FL310",
                List.of("FL300", "FL290", "o Question 5 of 122", "ID:"),
                0,
                instrumentRating),
            new Question(
                null,
                "Excluding RVSM, an appropriate flight level (FL) for an IFR flight in accordance with the semi-circular height rules on a Magnetic Course 180 is: o FL85 FL90",
                List.of("FL100", "FL115", "o Question 6 of 122", "ID:"),
                0,
                instrumentRating),
            new Question(
                null,
                "(See attachment IC-033-032) When following the track 185(M) from TALLA (TLA N55°30.0′, W003°21.2′), the aircraft is following what type of route? o RNAV route RNP route",
                List.of("Non-RNAV route", "Direct route", "o Question 7 of 122", "ID:"),
                0,
                instrumentRating),
            new Question(
                null,
                "(See attachment ic-033-095) The airway with designator UG851 is what type of airway? o Conditional route RNAV ATS route",
                List.of(
                    "Route usable by non B-RNAV equipped aircraft",
                    "Direct route",
                    "o Question 8 of 122",
                    "ID:"),
                0,
                instrumentRating),
            new Question(
                null,
                "o According GM1 CAT.OP.MPA.145 (b), MOCA is the sum of the maximum terrain or obstacle elevation plus (1) ____ for elevation up to and including 6000 ft, or (2) ____ for elevation exceeding 6000 ft rounded up to the next 100 ft. o (1) 3000 ft, (2) 2000 ft (1) 500 ft, (2) 1000 ft",
                List.of(
                    "(1) 1000 ft, (2) 2000 ft",
                    "(1) 2000 ft, (2) 1000 ft",
                    "o Question 9 of 122",
                    "ID:"),
                0,
                instrumentRating),
            new Question(
                null,
                "According GM1 CAT.OP.MPA.145 (b), the lowest MOCA to be indicated is… o 1000 ft (300 m) 500 ft (150 m)",
                List.of("2000 ft (600 m)", "5000 ft (450 m)", "o Question 10 of 122", "ID:"),
                0,
                instrumentRating),
            new Question(
                null,
                "An airway is marked “3500T 2100 a” means the: o Minimum En-route Altitude (MEA) is 3500 ft. Airway is a low level link route from 2100 ft to 3500 ft AMSL",
                List.of(
                    "Minimum Obstacle Clearance Altitude (MOCA) is 3500 ft.",
                    "Base of the Airway is 3500 ft MSL.",
                    "o Question 11 of 122",
                    "ID:"),
                0,
                instrumentRating),
            new Question(
                null,
                "An airway is marked “FL80 1500 a” means the… o Base of the airway is 1500 ft MSL. Airway extends from 1500 ft MSL to FL80.",
                List.of(
                    "Minimum En-route Altitude (MEA) is FL80.",
                    "Minimum radio reception altitude (MRA) is 1500 ft AMSL.",
                    "o Question 12 of 122",
                    "o ID:"),
                0,
                instrumentRating),
            new Question(
                null,
                "An airway is marked “5000 2900a” means the… o Maximum Authorised Altitude (MAA). Minimum En-route Altitude (MEA).",
                List.of(
                    "Minimum Obstacle Clearance Altitude (MOCA).",
                    "Minimum Holding Altitude (MHA).",
                    "o Question 13 of 122",
                    "ID:"),
                0,
                instrumentRating));

    userRepository.save(adminUser);
    departmentRepository.save(department);
    employeeRepository.saveAll(List.of(empOne, empTwo));
    addressRepository.saveAll(List.of(address, addressTwo));
    examStatusRepository.saveAll(
        List.of(readyStatus, inProgressStatus, completedStatus, reviewedStatus));
    examRepository.saveAll(exams);
    subjectRepository.saveAll(subjects);
    questionRepository.saveAll(questions);
  }
}
