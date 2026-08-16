package com.pri.twitterclone.service;

import com.pri.twitterclone.entites.User;
import com.pri.twitterclone.repository.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;

@Service
public interface UserService {
    User getUserById(Long id) ;
    User createUser(User user);
    User updateUser (Long id, User user);
    List<User> getAllUser(); // need to upgrade it to in form of pagination sorting search and page size.

}
