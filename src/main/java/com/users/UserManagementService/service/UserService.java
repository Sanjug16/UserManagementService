package com.users.UserManagementService.service;

import com.users.UserManagementService.Repository.RoleRepository;
import com.users.UserManagementService.Repository.UserRepository;
import com.users.UserManagementService.dto.UserResponse;
import com.users.UserManagementService.exception.UserNotFoundException;
import com.users.UserManagementService.model.Role;
import com.users.UserManagementService.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private RoleRepository roleRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepo;

    public UserResponse getUserByEmail(String email) {
        Optional<User> userOptional = userRepo.findByEmail(email);
        if (userOptional.isEmpty()) {
            throw new UserNotFoundException("User not found with email: " + email);
        }
        User user = userOptional.get();
//        String roles = user.getRoles().stream()
//                .map(Role::getRoleName)
//                .collect(Collectors.joining(", "));
        Set<String> roles = user.getRoles().stream().map((role)->role.getRoleName()).collect(Collectors.toSet());

        return new UserResponse( user.getUserId(),user.getEmail(), roles, user.getName(), user.getPassword());
    }

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public Optional<User> getAUser(Long userId) {
        return userRepo.findById(userId);
    }

    public User addUser(User newUser, Set<String> roleNames) {
        Set<Role> roles = roleNames.stream()
                .map(roleName -> {
                    Role role = roleRepo.findByRoleName(roleName);
                    if (role == null) {
                        throw new IllegalArgumentException("Role not found: " + roleName);
                    }
                    return role;
                })
                .collect(Collectors.toSet());
        newUser.setRoles(roles);
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        return userRepo.saveAndFlush(newUser);
    }

    public User updateUser(User editedUser, Set<String> roleNames) {
        Set<Role> roles = roleNames.stream()
                .map(roleName -> {
                    Role role = roleRepo.findByRoleName(roleName);
                    if (role == null) {
                        throw new IllegalArgumentException("Role not found: " + roleName);
                    }
                    return role;
                })
                .collect(Collectors.toSet());
        editedUser.setRoles(roles);
        return userRepo.save(editedUser);
    }

    public void deleteUser(Long userId) {
        userRepo.deleteById(userId);
    }
}
