package com.mdbackend.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import com.mdbackend.backend.model.Idea;
import com.mdbackend.backend.model.User;


@Repository
public interface IdeaRepository extends JpaRepository<Idea, Long> {
    
    // Find all ideas by a specific user
    List<Idea> findByUser(User user);
    
    // Find all ideas with user data (avoid N+1 problem)
    @Query("SELECT i FROM Idea i JOIN FETCH i.user ORDER BY i.createdAt DESC")
    List<Idea> findAllWithUser();
    
    // Find ideas by title containing keyword
    List<Idea> findByTitleContainingIgnoreCase(String keyword);
    
    // Find ideas by user ID
    @Query("SELECT i FROM Idea i WHERE i.user.id = :userId ORDER BY i.createdAt DESC")
    List<Idea> findByUserId(@Param("userId") Long userId);
    
    // Count ideas by user ID
    long countByUserId(Long userId);
}