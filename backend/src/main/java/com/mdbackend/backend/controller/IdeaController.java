package com.mdbackend.backend.controller;

import com.mdbackend.backend.model.Idea;
import com.mdbackend.backend.model.User;
import com.mdbackend.backend.repository.UserRepository;
import com.mdbackend.backend.service.IdeaService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/ideas")
public class IdeaController {

    private final IdeaService ideaService;
    private final UserRepository userRepository;

    public IdeaController(IdeaService ideaService, UserRepository userRepository) {
        this.ideaService = ideaService;
        this.userRepository = userRepository;
    }

    @PostMapping
    public ResponseEntity<Idea> createIdea(@RequestBody CreateIdeaRequest request) {
         System.out.println("--- DEBUG: Inside createIdea endpoint ---");
        System.out.println("Request Payload: Title=" + request.getTitle() + ", Slogan=" + request.getSlogan());

        // Dapatkan username dari principal
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        System.out.println("Authenticated username: " + username);
        
        // Dapatkan user dari database
        User currentUser = userRepository.findByUsername(username)
                .map(user -> {System.out.println("Current user found: " + user.getUsername()); return user;})
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        // Buat idea dengan user yang benar
        Idea idea = ideaService.createIdea(
            request.getTitle(),
            request.getSlogan(),
            request.getDescription(),
            currentUser.getId()
        );
        System.out.println("Idea created: " + idea.getTitle());
        
        return ResponseEntity.ok(idea);
    }

    @GetMapping
    public ResponseEntity<List<Idea>> getAllIdeas() {
         System.out.println("--- DEBUG: Inside getAllIdeas endpoint ---");

        return ResponseEntity.ok(ideaService.getAllIdeas());
    }

    @GetMapping("/my-ideas")
    public ResponseEntity<List<Idea>> getMyIdeas() {
        // Dapatkan username dari principal
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        System.out.println("Authenticated username: " + username);
        
        // Dapatkan user dari database
        User currentUser = userRepository.findByUsername(username)
                .map(user -> {System.out.println("Current user found: " + user.getUsername()); return user;})
                .orElseThrow(() -> new RuntimeException("User not found"));
        System.out.println("User ID: " + currentUser.getId());
        
        return ResponseEntity.ok(ideaService.getUserIdeas(currentUser.getId()));
    }
}

class CreateIdeaRequest {
    private String title;
    private String slogan;
    private String description;
    
    // getters and setters
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getSlogan() { return slogan; }
    public void setSlogan(String slogan) { this.slogan = slogan; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}