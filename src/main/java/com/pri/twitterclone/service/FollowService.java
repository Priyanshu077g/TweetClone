package com.pri.twitterclone.service;

import com.pri.twitterclone.entites.Follow;
import com.pri.twitterclone.entites.User;
import java.util.List;


public interface FollowService {
     List<User> followers(Long id);
     String unfollow(Long followerId, Long followingId);
     List<User> following(Long id);
     Follow newFollow(Long followerId, Long followingId);
}
