package com.users.UserManagementService.dto;

import com.users.UserManagementService.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private Long userId;
    private String email;
    private Set<String> role;
    private String name;
    private String password;
}

