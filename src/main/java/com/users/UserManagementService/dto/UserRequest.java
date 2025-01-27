package com.users.UserManagementService.dto;

import com.users.UserManagementService.model.User;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.Set;

@Data
public class UserRequest {
    @Valid
    private User user;

    @NotEmpty(message = "Role names must not be empty")
    private Set<String> roleNames;
}