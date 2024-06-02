package com.example.social_media_app.repository;

import com.example.social_media_app.enums.FriendshipStatus;
import com.example.social_media_app.modal.Friendship;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FriendshipRepository extends JpaRepository<Friendship, Long> {
    List<Friendship> findByUserId(Long userId);
    List<Friendship> findByFriendId(Long friendId);
    List<Friendship> findByUserIdAndStatus(Long userId, FriendshipStatus status);
    List<Friendship> findByFriendIdAndStatus(Long friendId, FriendshipStatus status);
}
