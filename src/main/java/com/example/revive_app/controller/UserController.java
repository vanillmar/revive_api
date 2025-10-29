/* Copyright (C)2025  Vanilson Marcos */
package com.example.revive_app.controller;

import com.example.revive_app.data.Permissions;
import com.example.revive_app.data.dto.ResponseDTO;
import com.example.revive_app.data.dto.UserRequestDTO;
import com.example.revive_app.data.dto.UserResponseDTO;
import com.example.revive_app.data.mapper.UserMapper;
import com.example.revive_app.exception.ResourceNotFoundException;
import com.example.revive_app.model.User;
import com.example.revive_app.service.UserService;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping("/stats/total")
    public ResponseEntity<ResponseDTO<Map<String, Long>>> getTotalQuestions() {
        ResponseDTO<Map<String, Long>> response = new ResponseDTO<>();
        Long totalUsers = userService.getTotalUsers();
        response.setData(Map.of("total", totalUsers));
        response.setSuccess(true);
        response.setMessage("Total users retrived successfully.");
        response.setStatus(HttpStatus.OK.value());
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAuthority('" + Permissions.READ_ME + "')")
    @GetMapping("/me")
    public ResponseEntity<ResponseDTO<UserResponseDTO>> getMe(Authentication authentication) {
        ResponseDTO<UserResponseDTO> response = new ResponseDTO<>();
        User user = userService.getMe(authentication.getPrincipal());
        UserResponseDTO userDto = userMapper.toResponse(user);
        response.setSuccess(true);
        response.setMessage("User retrieved successfully");
        response.setStatus(HttpStatus.OK.value());
        response.setData(userDto);

        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/{id}/upload-profile-picture")
    public ResponseEntity<ResponseDTO<Map<String, String>>> uploadProfilePicture(
            @PathVariable UUID id,
            @RequestParam("file") MultipartFile file
    ) {
        ResponseDTO<Map<String, String>> response = new ResponseDTO<>();
        String url = userService.saveAvatar(id, file);
        response.setData(Map.of("url", url));
        response.setMessage("profile picture updateed sucessfully");
        response.setStatus(HttpStatus.CREATED.value());
        response.setSuccess(true);
        return ResponseEntity.ok().body(response);
    }

    @PreAuthorize("hasAuthority('" + Permissions.READ_USERS + "')")
    @GetMapping
    public ResponseEntity<ResponseDTO<List<UserResponseDTO>>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        List<UserResponseDTO> usersDTO = userMapper.toResponseList(users);
        ResponseDTO<List<UserResponseDTO>> response = new ResponseDTO<>();
        response.setSuccess(true);
        response.setMessage("Users retrieved successfully");
        response.setStatus(HttpStatus.OK.value());
        response.setData(usersDTO);
        return ResponseEntity.ok().body(response);
    }

    @PreAuthorize("hasAuthority('" + Permissions.READ_USER + "')")
    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO<UserResponseDTO>> getUserById(@PathVariable String id) {
        UUID newId = UUID.fromString(id);
        UserResponseDTO userResponse = userService.getUserById(newId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        ResponseDTO<UserResponseDTO> response = new ResponseDTO<>();
        response.setData(userResponse);
        response.setSuccess(true);
        response.setMessage("User retrived successfully.");
        response.setStatus(HttpStatus.OK.value());
        
        return ResponseEntity.ok().body(response);
    }

    @PreAuthorize("hasAuthority('" + Permissions.CREATE_USER + "')")
    @PostMapping
    public ResponseEntity<ResponseDTO<UserResponseDTO>> createUser(@RequestBody UserRequestDTO userDTO) {
        User createdUser = userService.createUser(userDTO);
        UserResponseDTO createdUserDTO = userMapper.toResponse(createdUser);
        ResponseDTO<UserResponseDTO> response = new ResponseDTO<>();
        response.setSuccess(true);
        response.setMessage("User created successfully");
        response.setStatus(HttpStatus.CREATED.value());
        response.setData(createdUserDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PreAuthorize("hasAuthority('" + Permissions.CREATE_USERS + "')")
    @PostMapping("/batch")
    public ResponseEntity<ResponseDTO<List<UserResponseDTO>>> createUsers(@RequestBody List<UserRequestDTO> users) {
        List<User> createdUsers = userService.createUsers(users);
        List<UserResponseDTO> createdUsersDTO = userMapper.toResponseList(createdUsers);
        ResponseDTO<List<UserResponseDTO>> response = new ResponseDTO<>();
        response.setSuccess(true);
        response.setMessage("Users created successfully");
        response.setStatus(HttpStatus.CREATED.value());
        response.setData(createdUsersDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PreAuthorize("hasAuthority('" + Permissions.UPDATE_USER + "')")
    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO<UserResponseDTO>> updateUser(@PathVariable UUID id,
            @RequestBody UserRequestDTO userDetails) {
        ResponseDTO<UserResponseDTO> response = new ResponseDTO<>();
        User updatedUser = userService.updateUser(id, userDetails);
        UserResponseDTO updatedUserDTO = userMapper.toResponse(updatedUser);
        response.setSuccess(true);
        response.setMessage("User updated successfully");
        response.setStatus(HttpStatus.OK.value());
        response.setData(updatedUserDTO);
        return ResponseEntity.ok().body(response);
    }

    @PreAuthorize("hasAuthority('" + Permissions.UPDATE_USERS + "')")
    @PutMapping("/batch")
    public ResponseEntity<ResponseDTO<List<UserResponseDTO>>> updateUsers(@RequestBody List<UserRequestDTO> users) {
        List<User> updateUsers = userService.updateUsers(users);
        List<UserResponseDTO> updatedUsersDTO = userMapper.toResponseList(updateUsers);
        ResponseDTO<List<UserResponseDTO>> response = new ResponseDTO<>();
        response.setSuccess(true);
        response.setMessage("Users updated successfully");
        response.setStatus(HttpStatus.OK.value());
        response.setData(updatedUsersDTO);
        return ResponseEntity.ok().body(response);
    }

    @PreAuthorize("hasAuthority('" + Permissions.DELETE_USER + "')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable UUID id) {
        boolean deleted = userService.deleteUser(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
