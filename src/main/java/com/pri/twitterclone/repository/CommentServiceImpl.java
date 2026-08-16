package com.pri.twitterclone.repository;

import com.pri.twitterclone.entites.Comment;
import com.pri.twitterclone.entites.Tweet;
import com.pri.twitterclone.entites.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class CommentServiceImpl implements CommentService{

    private final TweetRepo tweetRepo;
    private final CommentRepo commentRepo;
    private final UserRepo userRepo;
    @Override
    public List<Comment> getAllCommentForTweet(Long tweetId) {
        return commentRepo.findAllCommentByTweetId(tweetId);
    }

    @Override
    public Comment postCommentForTweet(Long userId,Long tweetId, Comment comment) {

        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Tweet tweet = tweetRepo.findById(tweetId)
                .orElseThrow(() -> new RuntimeException("Tweet not found"));

        comment.setUserComment(user);
        comment.setTweetId(tweet);

        return commentRepo.save(comment);
    }

    @Override
    public String deleteComment(Long commentId) {
        Comment comment = commentRepo.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comment not found"));

        commentRepo.delete(comment);

        return "Comment deleted successfully";
    }
}
