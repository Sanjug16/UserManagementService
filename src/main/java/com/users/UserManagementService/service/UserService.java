package com.users.UserManagementService.service;

import com.users.UserManagementService.Repository.UserRepository;
import com.users.UserManagementService.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;

    public List<User> getAllUsers(){
        return userRepo.findAll();
    }

    public Optional<User> getAUser(Long userId){
        return userRepo.findById(userId);
    }

    public User addUser(User newUSer){
        return userRepo.saveAndFlush(newUSer);
    }

    public User updateUser(User editedUSer){
        return userRepo.save(editedUSer);
    }

    public void deleteUser(Long userId){
        userRepo.deleteById(userId);
    }
}
