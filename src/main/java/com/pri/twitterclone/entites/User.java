package com.pri.twitterclone.entites;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    id, username, email, displayName, bio, createdAt, updatedAt
    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = true, unique = false)
    private String bio;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Tweet> tweets = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy= "follower", cascade =  CascadeType.ALL, orphanRemoval = true)
    private Set<Follow> follower = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy= "following", cascade =  CascadeType.ALL, orphanRemoval = true)
    private Set<Follow> following = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "userLike")
    private Set<Like> userLike = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "tweetLike")
    private Set<Like> tweetLike = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "userComment")
    private Set<User> userComment = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "tweetId")
    private Set<Tweet> tweetId = new HashSet<>();

}
