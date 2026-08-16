package com.pri.twitterclone.repository;

import com.pri.twitterclone.entites.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TweetRepo extends JpaRepository<Tweet, Long> {

}
