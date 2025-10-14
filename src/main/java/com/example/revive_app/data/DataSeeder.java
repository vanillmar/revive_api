/* Copyright (C)2025  Vanilson Marcos */
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
import com.example.revive_app.model.Student;
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
import com.example.revive_app.repository.StudentRepository;
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

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ExamStatusRepository examStatusRepository;

    @Autowired
    private ExamRepository examRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Override
    public void run(ApplicationArguments args) {

        Department department = new Department();
        department.setName("Human Resources");
        department.setDescription("Handles employee relations and benefits");

        User adminUser = new Admin();
        adminUser.setUsername("admin");
        adminUser.setPassword(passwordEncoder.encode("admin123"));
        adminUser.setEmail("admin@system.com");

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

        Student studentOne = new Student();
        studentOne.setUsername("marcos");
        studentOne.setPassword(passwordEncoder.encode("test_"));
        studentOne.setFirstname("Vanilson");
        studentOne.setLastname("Marcos");
        studentOne.setEmail("vanilson@marcos.ao");

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

            permissionRepository.saveAll(List.of(createUsers, createUser, deleteUser, readUsers, readUser, readMe,
                    readEmployee, readEmployees, createEmployee, createEmployees, updateEmployee, updateEmployees,
                    deleteEmployee, readAddress, readAddresses, createAddress, createAddresses, updateAddress,
                    updateAddresses, deleteAddress, readDepartment, readDepartments, createDepartment,
                    createDepartments, updateDepartment, updateDepartments, deleteDepartment, readExams));

            // Criar roles
            Role admin = new Role();
            admin.setName(Roles.ADMIN);
            admin.setDescription("Administrator with all permissions");
            admin.setPermissions(Set.of(createUsers, createUser, deleteUser, readUsers, readUser, readMe, readEmployee,
                    readEmployees, createEmployee, createEmployees, updateEmployee, updateEmployees, deleteEmployee,
                    readAddress, readAddresses, createAddress, createAddresses, updateAddress, updateAddresses,
                    deleteAddress, readDepartment, readDepartments, createDepartment, createDepartments,
                    updateDepartment, updateDepartments, deleteDepartment, readExams));

            Role moderator = new Role();
            moderator.setName(Roles.MODERATOR);
            moderator.setDescription("Moderator with limited permissions");
            moderator.setPermissions(Set.of(readUser, readMe, readAddress, readDepartment, readExams));

            Role user = new Role();
            user.setName(Roles.USER);
            user.setDescription("Regular user with view-only permissions");
            user.setPermissions(Set.of(readMe, readAddress, readDepartment, readExams));

            roleRepository.saveAll(List.of(admin, moderator, user));

            empOne.setRoles(Set.of(roleRepository.findByName(Roles.MODERATOR)
                    .orElseThrow(() -> new RuntimeException("Moderator role not found"))));
            adminUser.setRoles(Set.of(roleRepository.findByName(Roles.ADMIN)
                    .orElseThrow(() -> new RuntimeException("Admin role not found"))));
            studentOne.setRoles(Set.of(roleRepository.findByName(Roles.USER)
                    .orElseThrow(() -> new RuntimeException("User role not found"))));
        }

        ExamStatus readyStatus = new ExamStatus(null, "Ready", "Ready to take");
        ExamStatus inProgressStatus = new ExamStatus(null, "In Progress", "Currently taking the exam");
        ExamStatus completedStatus = new ExamStatus(null, "Completed", "Exam completed");
        ExamStatus reviewedStatus = new ExamStatus(null, "Reviewed", "Exam has been reviewed");

        Subject instrumentRating = new Subject(null, "instrument Rating", "Instruments and Electronics", "—");
        Subject airlaw = new Subject(null, "airlaw", "Air Law", "—");
        Subject humanPerformance = new Subject(null, "human-performance", "Human Performance", "—");
        Subject aircraftTechnical = new Subject(null, "aircraft-technical", "Aircraft Technical General", "—");
        Subject flightPlanning = new Subject(null, "flight-planning", "Flight Planning and Performance", "—");
        Subject meteorology = new Subject(null, "meteorology", "Meteorology", "—");
        Subject generalNavigation = new Subject(null, "general-navigation", "General Navigation", "—");
        Subject radioAids = new Subject(null, "radio-aids", "Radio Aids", "—");

        List<Subject> subjects = List.of(instrumentRating, airlaw, humanPerformance, aircraftTechnical, flightPlanning,
                meteorology, generalNavigation, radioAids);

        Exam instrumentsRatingExam = new Exam(null, instrumentRating, instrumentRating.getName(), "—", 72, 9_000,
                readyStatus);
        Exam airlawExam = new Exam(null, airlaw, airlaw.getName(), "—", 72, 9_000, readyStatus);
        Exam humanPerformanceExam = new Exam(null, humanPerformance, humanPerformance.getName(), "—", 72, 9_000,
                readyStatus);
        Exam aircraftTechnicalExam = new Exam(null, aircraftTechnical, aircraftTechnical.getName(), "—", 72, 9_000,
                inProgressStatus);
        Exam flightPlanningExam = new Exam(null, flightPlanning, flightPlanning.getName(), "—", 72, 9_000, readyStatus);
        Exam meteorologyExam = new Exam(null, meteorology, meteorology.getName(), "—", 72, 9_000, readyStatus);
        Exam generalNavigationExam = new Exam(null, generalNavigation, generalNavigation.getName(), "—", 72, 9_000,
                completedStatus);
        Exam radioAidsExam = new Exam(null, radioAids, radioAids.getName(), "—", 72, 9_000, readyStatus);

        // exams to insert
        List<Exam> exams = List.of(instrumentsRatingExam, airlawExam, humanPerformanceExam, aircraftTechnicalExam,
                flightPlanningExam, meteorologyExam, generalNavigationExam, radioAidsExam);

        List<Question> questions = InstrumentRatingQuestions.getQuestions(instrumentRating);

        userRepository.save(adminUser);
        departmentRepository.save(department);
        employeeRepository.saveAll(List.of(empOne, empTwo));
        studentRepository.saveAll(List.of(studentOne));
        addressRepository.saveAll(List.of(address, addressTwo));
        examStatusRepository.saveAll(List.of(readyStatus, inProgressStatus, completedStatus, reviewedStatus));
        subjectRepository.saveAll(subjects);
        examRepository.saveAll(exams);
        questionRepository.saveAll(questions);
    }
}
