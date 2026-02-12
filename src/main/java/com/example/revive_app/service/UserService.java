package com.example.revive_app.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.multipart.MultipartFile;

import com.example.revive_app.model.User;

public interface UserService {

    boolean existsById(UUID id);

    boolean existsByUsername(String username);

    Optional<UserDetails> findByUsername(String username);

    User getMe(Object principal);

    List<User> getAllUsers();

    Optional<User> getUserById(UUID id);

    User create(User user);

    List<User> createUsers(List<User> users);

    User update(UUID id, User incoming);

    List<User> updateUsers(List<User> users);

    boolean deleteById(UUID id);

    Long getTotalUsers();

    Long getTotalActiveUsers();

    Long getTotalInactiveUsers();

    String saveAvatar(UUID id, MultipartFile file);

    Page<User> findAllWithFilters(String search, Pageable pageable);
}
