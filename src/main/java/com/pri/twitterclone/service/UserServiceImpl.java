package com.pri.twitterclone.service;

import com.pri.twitterclone.entites.User;
import com.pri.twitterclone.exceptionHandler.UserAlreadyExistsException;
import com.pri.twitterclone.exceptionHandler.UserNotFoundException;
import com.pri.twitterclone.repository.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepo userRepo;

    @Override
    public User getUserById(Long id){
        return userRepo.findById(id).orElseThrow(() -> new UserNotFoundException("User not Found"));

    }

    @Override
    public User createUser(User user) {
        if(userRepo.existsByEmail(user.getEmail())) {
            throw new UserAlreadyExistsException("Email already exists");
        }
        User newUser = user;
        userRepo.save(newUser);
        return newUser;
    }

    @Override
    public User updateUser(Long id, User user) {
        User u = userRepo.findById(id).orElseThrow(() -> new UserNotFoundException("User not Found"));
        u.setBio(user.getBio());
        u.setUsername(user.getUsername());
        userRepo.save(u);
        return u;
    }

    @Override
    public List<User> getAllUser() {
        return userRepo.findAll();
    }
}
