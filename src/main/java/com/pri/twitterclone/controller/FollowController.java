package com.pri.twitterclone.controller;

import com.pri.twitterclone.entites.Follow;
import com.pri.twitterclone.entites.User;
import com.pri.twitterclone.service.FollowService;
import com.pri.twitterclone.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class FollowController {

    private final FollowService followService;
    private final UserService userService;

    @GetMapping("/{userId}/following")
    public ResponseEntity<List<User>> getAllFollowing(@PathVariable Long userId) {
        return ResponseEntity.ok(followService.following(userId));
    }

    @GetMapping("/{userId}/followers")
    public ResponseEntity<List<User>> getAllFollowers(@PathVariable Long userId) {
        return ResponseEntity.ok(followService.followers(userId));
    }

    @DeleteMapping("/{userId}/following/{targetId}")
    public ResponseEntity<String> unfollow(@PathVariable Long userId, @PathVariable Long targetId) {
        return ResponseEntity.ok(followService.unfollow(userId, targetId));
    }

    @PostMapping("/{userId}/following/{targetId}")
    public ResponseEntity<Follow> newFollower(@PathVariable Long userId, @PathVariable Long targetId) {
        return ResponseEntity.ok(followService.newFollow(userId, targetId));
    }
}
