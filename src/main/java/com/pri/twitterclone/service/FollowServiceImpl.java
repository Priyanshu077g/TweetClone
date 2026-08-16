package com.pri.twitterclone.service;

import com.pri.twitterclone.entites.Follow;
import com.pri.twitterclone.entites.User;
import com.pri.twitterclone.repository.FollowRepo;
import com.pri.twitterclone.repository.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FollowServiceImpl implements  FollowService{


    private final FollowRepo followRepo;
    private final UserRepo userRepo;

    @Override
    public List<User> followers(Long userId) {
        return  followRepo.findAllByFollowingId(userId)
                .stream().map(Follow::getFollower).toList();
    }

    @Override
    public String unfollow(Long followerId, Long followingId) {

        Follow follow = followRepo
                .findByFollowerIdAndFollowingId(followerId, followingId)
                .orElseThrow(() ->
                        new RuntimeException("You are not following this user"));

        followRepo.delete(follow);

        return "Krdiya unfollow 😊";
    }

    @Override
    public List<User> following(Long userId) {
        return followRepo.findAllFollower(userId)
                .stream()
                .map(Follow::getFollowing)
                .toList();

    }

    @Override
    public Follow newFollow(Long followerId, Long followingId) {

        User follower = userRepo.findById(followerId)
                .orElseThrow(() -> new RuntimeException("Follower not found"));

        User following = userRepo.findById(followingId)
                .orElseThrow(() -> new RuntimeException("User to follow not found"));

        if (followRepo.existsFollow(followerId, followingId)) {
            throw new RuntimeException("Already following this user");
        }



        Follow follow = new Follow();

        follow.setFollower(follower);
        follow.setFollowing(following);

        return followRepo.save(follow);
    }
}
