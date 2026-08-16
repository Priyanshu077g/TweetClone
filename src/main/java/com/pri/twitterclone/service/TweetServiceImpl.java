package com.pri.twitterclone.service;

import com.pri.twitterclone.dtos.CreateTweetRequest;
import com.pri.twitterclone.dtos.UpdateTweetRequest;
import com.pri.twitterclone.entites.Tweet;
import com.pri.twitterclone.entites.User;
import com.pri.twitterclone.repository.TweetRepo;
import com.pri.twitterclone.repository.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TweetServiceImpl implements TweetService{

    private final TweetRepo tweetRepo;
    private final UserRepo userRepo;

    @Override
    public Tweet createTweet(CreateTweetRequest request) {
        Long userId = request.getUserId();

        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Tweet tweet =  new Tweet();
        tweet.setContent(request.getContent());
        tweet.setUser(user);

        return tweetRepo.save(tweet);
    }

    @Override
    public Tweet getTweet(Long id) {
        return tweetRepo.findById(id).orElseThrow(() -> new RuntimeException("Tweet id is not valid"));
    }

    @Override
    public Tweet updateTweet(Long id, UpdateTweetRequest request) {
        Tweet tweet = tweetRepo.findById(id).orElseThrow(() -> new RuntimeException("Tweet id is not valid"));
        tweet.setContent(request.getContent());
        tweetRepo.save(tweet);
        return tweet;
    }

    @Override
    public void deleteTweet(Long id) {
        Tweet tweet = tweetRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Tweet not found"));
        tweetRepo.deleteById(id);

    }

    @Override
    public List<Tweet> getAllTweet() {
        return tweetRepo.findAll();
    }
}
