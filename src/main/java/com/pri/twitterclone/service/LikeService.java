package com.pri.twitterclone.service;


import com.pri.twitterclone.entites.Like;

public interface LikeService {
    Like likeTweet(Long userId, Long tweetId);
    String unLikeTweet(Long userId, Long tweetId);
    Integer getLikeCount(Long tweetId);
}
