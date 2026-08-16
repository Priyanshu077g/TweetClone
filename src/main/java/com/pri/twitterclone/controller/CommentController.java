package com.pri.twitterclone.controller;

import com.pri.twitterclone.entites.Comment;
import com.pri.twitterclone.repository.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
@AllArgsConstructor
public class CommentController {

    private final CommentService commentService;

    // Get all comments for a tweet
    @GetMapping("/tweet/{tweetId}")
    public List<Comment> getAllCommentForTweet(@PathVariable Long tweetId) {
        return commentService.getAllCommentForTweet(tweetId);
    }

    // Post comment on a tweet
    @PostMapping("/user/{userId}/tweet/{tweetId}")
    public Comment postCommentForTweet(
            @PathVariable Long userId,
            @PathVariable Long tweetId,
            @RequestBody Comment comment) {

        return commentService.postCommentForTweet(userId, tweetId, comment);
    }

    // Delete comment
    @DeleteMapping("/{commentId}")
    public String deleteComment(@PathVariable Long commentId) {
        return commentService.deleteComment(commentId);
    }
}
