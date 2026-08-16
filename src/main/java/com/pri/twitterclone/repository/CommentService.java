package com.pri.twitterclone.repository;

import com.pri.twitterclone.entites.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> getAllCommentForTweet(Long tweetId);
    Comment postCommentForTweet(Long userId,Long tweetId, Comment comment);
    String deleteComment(Long commentId);

}
