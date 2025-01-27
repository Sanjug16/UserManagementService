package com.users.UserManagementService.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users_details")
@Data
//@AllArgsConstructor
//@NoArgsConstructor

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @NotBlank(message = "Name is required")
    @Column(name = "user_name")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    @Column(unique = true, name = "user_email")
    private String email;

    @NotBlank(message = "Phone number is required")
    @Column(name = "user_phone")
    private String phoneNumber;

    @Column(name = "user_address")
    private String address;

    @NotNull(message = "Registration date is required")
    @Temporal(TemporalType.DATE)
    @Column(name = "user_registration_date")
    private Date registrationDate;

    @NotBlank(message = "Password is required")
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    public User() {
    }

    public User(Long userId, String name, String email, String phoneNumber, String address, Date registrationDate, String password, Set<Role> roles) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.registrationDate = registrationDate;
        this.password = password;
        this.roles = roles;
    }
}