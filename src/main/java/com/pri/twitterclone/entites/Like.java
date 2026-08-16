package com.pri.twitterclone.entites;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "likes")
public class Like {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    @Column
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userLike")
    @JsonIgnore
    private User userLike;

    @ManyToOne(fetch =  FetchType.EAGER)
    @JoinColumn(name = "tweetLike")
    @JsonIgnore
    private Tweet tweetLike;
}
