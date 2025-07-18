package com.mdbackend.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import com.mdbackend.backend.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;





@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Tambahkan method ini di UserRepository
    
    
    // Correct method names with proper case sensitivity
    boolean existsByUsername(String username);  // must match field name exactly
    
    boolean existsByEmail(String email);  // must match field name exactly
    
    Optional<User> findByUsername(String username);

    @Override
    @NonNull
    Optional<User> findById(@NonNull Long id);
    
    
    Optional<User> findByEmail(String email);
    
    // Custom query to find users with their ideas (eager loading)
    @Query("SELECT u FROM User u LEFT JOIN FETCH u.ideas WHERE u.id = :userId")
    Optional<User> findByIdWithIdeas(@Param("userId") Long userId);

    // Photo profile query
    @Query("SELECT u.photoProfile FROM User u WHERE u.id = :userId")
    Optional<String> findPhotoProfileById(@Param("userId") Long userId);
}