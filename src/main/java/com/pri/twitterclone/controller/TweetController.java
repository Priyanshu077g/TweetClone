package com.pri.twitterclone.controller;

import com.pri.twitterclone.dtos.CreateTweetRequest;
import com.pri.twitterclone.dtos.UpdateTweetRequest;
import com.pri.twitterclone.entites.Tweet;
import com.pri.twitterclone.service.TweetService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tweet")
@AllArgsConstructor
public class TweetController {
    private final TweetService tweetService;

    @PostMapping
    public ResponseEntity<Tweet> createTweet(@Valid  @RequestBody CreateTweetRequest  request) {
        return ResponseEntity.ok(tweetService.createTweet(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tweet> getTweet(@Valid @PathVariable Long id) {
        return ResponseEntity.ok(tweetService.getTweet(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Tweet> updateTweet(@Valid @PathVariable Long id, @Valid @RequestBody UpdateTweetRequest tweet) {
        return ResponseEntity.ok(tweetService.updateTweet(id, tweet));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTweet(@Valid @PathVariable Long id) {
         tweetService.deleteTweet(id);
         return ResponseEntity.ok("Tweet deleted Successfully.");
    }
    @GetMapping("")
    public ResponseEntity<List<Tweet>> getAllTweet() {
        return ResponseEntity.ok(tweetService.getAllTweet());
    }
}
