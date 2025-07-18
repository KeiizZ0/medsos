package com.mdbackend.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import lombok.Data;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "ideas")
public class Idea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "slogan", nullable = false)
    private String slogan;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "image", nullable = true)
    private String image;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = true)
    private LocalDateTime updatedAt;
}
