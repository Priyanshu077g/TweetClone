package com.pri.twitterclone.controller;

import com.pri.twitterclone.entites.User;
import com.pri.twitterclone.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping("/api")

public class UserController {

    private final UserService userService;

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserByID(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.createUser(user));
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        return  ResponseEntity.ok(userService.updateUser(id,user));
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> giveAllUser() {
        return ResponseEntity.ok(userService.getAllUser());
    }

}
