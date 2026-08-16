package com.pri.twitterclone.repository;

import com.pri.twitterclone.entites.Follow;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FollowRepo extends JpaRepository<Follow, Long> {
    @Query("select f from Follow f where f.following.id = :userId")
    List<Follow> findAllByFollowingId(@Param("userId") Long userId);

    @Modifying
    @Transactional
    @Query("delete from Follow f where f.following.id = :userId")
    void removeFollowerUserId(@Param("userId") Long userId);

    @Query("select f from Follow f where f.follower.id = :userId")
    List<Follow> findAllFollower(@Param("userId") Long userId);

    //
    Optional<Follow> findByFollowerIdAndFollowingId(
            Long followerId,
            Long followingId
    );

    @Query("""
       SELECT COUNT(f) > 0
       FROM Follow f
       WHERE f.follower.id = :followerId
       AND f.following.id = :followingId
       """)
    boolean existsFollow(
            @Param("followerId") Long followerId,
            @Param("followingId") Long followingId
    );
}
