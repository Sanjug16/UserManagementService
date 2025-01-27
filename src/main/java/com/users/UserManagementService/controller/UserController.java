package com.users.UserManagementService.controller;

import com.users.UserManagementService.dto.UserRequest;
import com.users.UserManagementService.dto.UserResponse;
import com.users.UserManagementService.model.User;
import com.users.UserManagementService.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/getUserByEmail")
    public UserResponse getUserByEmail(@RequestParam String email) {
        return userService.getUserByEmail(email);
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable Long userId) {
        User user = userService.getAUser(userId).orElse(null);

        if (user == null) {
            return ResponseEntity.status(404).body("User not found with ID: " + userId);
        }

        return ResponseEntity.ok(user);
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserRequest userRequest) {
        if (userRequest.getRoleNames() == null || userRequest.getRoleNames().isEmpty()) {
            return ResponseEntity.badRequest().body("Role names are required.");
        }
        return ResponseEntity.status(201).body(userService.addUser(userRequest.getUser(), userRequest.getRoleNames()));
    }

    @PutMapping
    public ResponseEntity<?> updateUser(@Valid @RequestBody UserRequest userRequest) {
        if (userRequest.getRoleNames() == null || userRequest.getRoleNames().isEmpty()) {
            return ResponseEntity.badRequest().body("Role names are required.");
        }
        return ResponseEntity.ok(userService.updateUser(userRequest.getUser(), userRequest.getRoleNames()));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId) {
        Optional<User> user = userService.getAUser(userId);
        if (user.isPresent()) {
            userService.deleteUser(userId);
            return ResponseEntity.ok("User deleted successfully");
        } else {
            return ResponseEntity.status(404).body("User not found with ID: " + userId);
        }
    }
}
