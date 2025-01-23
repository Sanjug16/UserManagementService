package com.users.UserManagementService.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "users_details")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private Long userId; // MySQL uses Long for IDs

    @NotBlank(message = "Name is required")
    @Column(name="user_name")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    @Column(unique = true,name = "user_email")
    private String email;

    @NotBlank(message = "Phone number is required")
    @Column(name="user_phone")
    private String phoneNumber;
    @Column(name="user_address")
    private String address;

    @NotNull(message = "Registration date is required")
    @Temporal(TemporalType.DATE)
    @Column(name="user_registration_date")
    private Date registrationDate;

    @NotBlank(message = "Password is required")
    private String password;

    @NotNull(message = "Type is required")
    @Enumerated(EnumType.STRING)
    @Column(name="user_type")
    private UserType type; // Enum for user type (USER or SERVICE_PROVIDER)
}