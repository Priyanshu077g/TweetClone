package com.pri.twitterclone.service;

import com.pri.twitterclone.entites.Like;
import com.pri.twitterclone.entites.Tweet;
import com.pri.twitterclone.entites.User;
import com.pri.twitterclone.repository.LikeRepo;
import com.pri.twitterclone.repository.TweetRepo;
import com.pri.twitterclone.repository.UserRepo;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class LikeServiceImpl implements LikeService{

    private final LikeRepo likeRepo;
    private final UserRepo userRepo;
    private final TweetRepo tweetRepo;
    @Override
    public Like likeTweet(Long userId, Long tweetId) {
        User user = userRepo.findById(userId).orElse(null);
        Tweet tweet = tweetRepo.findById(tweetId).orElse(null);
        if(user==null || tweet==null) {
            throw new RuntimeException("User/Tweet not found");
        }
        if (likeRepo.existsByUserLikeIdAndTweetLikeId(userId, tweetId)) {
            throw new RuntimeException("Already liked");
        }
        Like like = new Like();

        like.setUserLike(user);
        like.setTweetLike(tweet);

        return likeRepo.save(like);
    }

    @Override
    public String unLikeTweet(Long userId, Long tweetId) {
        Like like = likeRepo.findByUserLikeIdAndTweetLikeId(userId, tweetId)
                .orElseThrow(() -> new RuntimeException("Like not found"));

        likeRepo.delete(like);
        return "Tweet unliked";
    }

    @Override
    public Integer getLikeCount(Long tweetId) {
        tweetRepo.findById(tweetId).orElseThrow(() -> new RuntimeException("Tweet not found"));

        return (int) likeRepo.countByTweetLikeId(tweetId);
    }
}
