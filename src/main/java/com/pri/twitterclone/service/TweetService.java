package com.pri.twitterclone.service;

import com.pri.twitterclone.dtos.CreateTweetRequest;
import com.pri.twitterclone.dtos.UpdateTweetRequest;
import com.pri.twitterclone.entites.Tweet;

import java.util.List;

public interface TweetService {
    Tweet createTweet(CreateTweetRequest tweet);
    Tweet getTweet(Long id);
    Tweet updateTweet(Long id, UpdateTweetRequest tweet);
    void deleteTweet(Long id);
    List<Tweet> getAllTweet();
    //list tweet pagination sorting searching page size.
}
