package com.pri.twitterclone.repository;

import com.pri.twitterclone.entites.Like;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface LikeRepo extends JpaRepository<Like, Long> {
    // Check whether user already liked this tweet
    boolean existsByUserLikeIdAndTweetLikeId(
            Long userId,
            Long tweetId
    );

    // Find the like of a particular user on a particular tweet
    Optional<Like> findByUserLikeIdAndTweetLikeId(
            Long userId,
            Long tweetId
    );

    // Count total likes on a tweet
    long countByTweetLikeId(Long tweetId);


}
