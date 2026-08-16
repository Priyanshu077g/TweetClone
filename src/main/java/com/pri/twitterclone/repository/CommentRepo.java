package com.pri.twitterclone.repository;

import com.pri.twitterclone.entites.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepo  extends JpaRepository<Comment, Long> {


    List<Comment> findAllCommentByTweetId(Long tweetId);
}
