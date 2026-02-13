/* Copyright (C)2025  Vanilson Marcos */
package com.example.revive_app.controller;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.example.revive_app.data.Permissions;
import com.example.revive_app.data.dto.ResponseDTO;
import com.example.revive_app.data.dto.UserRequestDTO;
import com.example.revive_app.data.dto.UserResponseDTO;
import com.example.revive_app.data.mapper.UserMapper;
import com.example.revive_app.exception.ResourceNotFoundException;
import com.example.revive_app.model.User;
import com.example.revive_app.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;
    private final MessageSource messageSource;

    public UserController(UserService userService, UserMapper userMapper, MessageSource messageSource) {
        this.userService = userService;
        this.userMapper = userMapper;
        this.messageSource = messageSource;
    }

    @GetMapping("/stats/total")
    public ResponseEntity<ResponseDTO<Map<String, Long>>> getTotalUsers(Locale locale) {
        ResponseDTO<Map<String, Long>> response = new ResponseDTO<>();
        Long totalUsers = userService.getTotalUsers();
        response.setData(Map.of("total", totalUsers));
        response.setSuccess(true);
        response.setMessage(messageSource.getMessage("user.stats.total.success", null, locale));
        response.setStatus(HttpStatus.OK.value());
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAuthority('" + Permissions.READ_ME + "')")
    @GetMapping("/me")
    public ResponseEntity<ResponseDTO<UserResponseDTO>> getMe(Authentication authentication, Locale locale) {
        ResponseDTO<UserResponseDTO> response = new ResponseDTO<>();
        User user = userService.getMe(authentication.getPrincipal());
        UserResponseDTO userDto = userMapper.toResponse(user);
        response.setSuccess(true);
        response.setMessage(messageSource.getMessage("user.me.success", null, locale));
        response.setStatus(HttpStatus.OK.value());
        response.setData(userDto);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/{id}/upload-profile-picture")
    public ResponseEntity<ResponseDTO<Map<String, String>>> uploadProfilePicture(@PathVariable UUID id,
            @RequestParam("file") MultipartFile file, Locale locale) {
        ResponseDTO<Map<String, String>> response = new ResponseDTO<>();
        String url = userService.saveAvatar(id, file);
        response.setData(Map.of("url", url));
        response.setMessage(messageSource.getMessage("user.profile.picture.updated", null, locale));
        response.setStatus(HttpStatus.CREATED.value());
        response.setSuccess(true);
        return ResponseEntity.ok().body(response);
    }

    // @PreAuthorize("hasAuthority('" + Permissions.READ_USERS + "')")
    @GetMapping
    public ResponseEntity<ResponseDTO<List<UserResponseDTO>>> getAllUsers(@RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int pageSize, @RequestParam(defaultValue = "createdAt") String sortBy,
            @RequestParam(defaultValue = "asc") String sortOrder, @RequestParam(defaultValue = "") String search, Locale locale) {

        Sort.Direction direction = sortOrder.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page - 1, pageSize, Sort.by(direction, sortBy));
        Page<User> userPage = userService.findAllWithFilters(search, pageable);

        List<UserResponseDTO> usersDTO = userMapper.toResponseList(userPage.getContent());
        ResponseDTO<List<UserResponseDTO>> response = new ResponseDTO<>();
        response.setSuccess(true);
        response.setMessage(messageSource.getMessage("user.all.success", null, locale));
        response.setStatus(HttpStatus.OK.value());  
        response.setData(usersDTO);

        response.setTotal((int) userPage.getTotalElements());
        response.setPage(page);
        response.setPageSize(pageSize);

        return ResponseEntity.ok().body(response);
    }

    // @PreAuthorize("hasAuthority('" + Permissions.READ_USER + "')")
    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO<UserResponseDTO>> getUserById(@PathVariable String id, Locale locale) {
        UUID newId = UUID.fromString(id);
        User user = userService.getUserById(newId).orElseThrow(() -> new ResourceNotFoundException(messageSource.getMessage("user.notfound", null, locale)));
        UserResponseDTO userResponse = userMapper.toResponse(user);
        ResponseDTO<UserResponseDTO> response = new ResponseDTO<>();
        response.setData(userResponse);
        response.setSuccess(true);
        response.setMessage(messageSource.getMessage("user.byid.success", null, locale));
        response.setStatus(HttpStatus.OK.value());

        return ResponseEntity.ok().body(response);
    }

    // @PreAuthorize("hasAuthority('" + Permissions.CREATE_USER + "')")
    @PostMapping
    public ResponseEntity<ResponseDTO<UserResponseDTO>> createUser(@RequestBody UserRequestDTO userDTO, Locale locale) {
        User incoming = userMapper.toEntity(userDTO);
        User createdUser = userService.create(incoming);
        UserResponseDTO createdUserDTO = userMapper.toResponse(createdUser);
        ResponseDTO<UserResponseDTO> response = new ResponseDTO<>();
        response.setSuccess(true);
        response.setMessage(messageSource.getMessage("user.create.success", null, locale));
        response.setStatus(HttpStatus.CREATED.value());
        response.setData(createdUserDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // @PreAuthorize("hasAuthority('" + Permissions.CREATE_USERS + "')")
    @PostMapping("/batch")
    public ResponseEntity<ResponseDTO<List<UserResponseDTO>>> dtos(@RequestBody List<UserRequestDTO> dtos, Locale locale) {
        List<User> users = userMapper.toEntityList(dtos);
        List<User> createdUsers = userService.createUsers(users);
        List<UserResponseDTO> createdUsersDTO = userMapper.toResponseList(createdUsers);
        ResponseDTO<List<UserResponseDTO>> response = new ResponseDTO<>();
        response.setSuccess(true);
        response.setMessage(messageSource.getMessage("user.create.batch.success", null, locale));
        response.setStatus(HttpStatus.CREATED.value());
        response.setData(createdUsersDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // @PreAuthorize("hasAuthority('" + Permissions.UPDATE_USER + "')")
    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO<UserResponseDTO>> updateUser(@PathVariable UUID id,
            @RequestBody UserRequestDTO dto, Locale locale) {
        User incoming = userMapper.toEntity(dto);
        User updatedUser = userService.update(id, incoming);
        ResponseDTO<UserResponseDTO> response = new ResponseDTO<>();
        UserResponseDTO updatedUserDTO = userMapper.toResponse(updatedUser);
        response.setSuccess(true);
        response.setMessage(messageSource.getMessage("user.update.success", null, locale));
        response.setStatus(HttpStatus.OK.value());
        response.setData(updatedUserDTO);
        return ResponseEntity.ok().body(response);
    }

    // @PreAuthorize("hasAuthority('" + Permissions.UPDATE_USERS + "')")
    @PutMapping("/batch")
    public ResponseEntity<ResponseDTO<List<UserResponseDTO>>> updateUsers(@RequestBody List<UserRequestDTO> dtos, Locale locale) {
        List<User> users = userMapper.toEntityList(dtos);
        List<User> updateUsers = userService.updateUsers(users);
        List<UserResponseDTO> updatedUsersDTO = userMapper.toResponseList(updateUsers);
        ResponseDTO<List<UserResponseDTO>> response = new ResponseDTO<>();
        response.setSuccess(true);
        response.setMessage(messageSource.getMessage("user.update.batch.success", null, locale));
        response.setStatus(HttpStatus.OK.value());
        response.setData(updatedUsersDTO);
        return ResponseEntity.ok().body(response);
    }

    // @PreAuthorize("hasAuthority('" + Permissions.DELETE_USER + "')")
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO<Map<String, UUID>>> deleteUser(@PathVariable UUID id, Locale locale) {
        boolean deleted = userService.deleteById(id);
        ResponseDTO<Map<String, UUID>> response = new ResponseDTO<>();
        if (deleted) {
            response.setMessage(messageSource.getMessage("user.delete.success", null, locale));
            response.setStatus(HttpStatus.OK.value());
            response.setData(Map.of("UserId", id));
            response.setSuccess(true);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            response.setMessage(messageSource.getMessage("user.notfound", null, locale));
            response.setStatus(HttpStatus.NOT_FOUND.value());
            response.setData(Map.of());
            response.setSuccess(false);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
}
