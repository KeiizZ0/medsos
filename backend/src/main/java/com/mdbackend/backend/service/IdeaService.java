package com.mdbackend.backend.service;

import com.mdbackend.backend.model.Idea;
import com.mdbackend.backend.model.User;
import com.mdbackend.backend.repository.IdeaRepository;
import com.mdbackend.backend.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;


@Service
public class IdeaService {

    private final IdeaRepository ideaRepository;
    private final UserRepository userRepository;

    public IdeaService(IdeaRepository ideaRepository, UserRepository userRepository) {
        this.ideaRepository = ideaRepository;
        this.userRepository = userRepository;
    }

    public Idea createIdea(String title, String slogan, String description, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        Idea idea = new Idea();
        idea.setTitle(title);
        idea.setSlogan(slogan);
        idea.setDescription(description);
        idea.setUser(user);
        idea.setCreatedAt(LocalDateTime.now());
        
        return ideaRepository.save(idea);
    }

    public List<Idea> getAllIdeas() {
        return ideaRepository.findAllWithUser();
    }

    public List<Idea> getUserIdeas(Long userId) {
        return ideaRepository.findByUserId(userId);
    }
}