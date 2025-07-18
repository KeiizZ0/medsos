package com.mdbackend.backend.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.Set;
import java.util.Collections;

@Entity
@Data
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;
    
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "photo_profile", nullable = true)
    private String photoProfile;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Idea> ideas;


    // UserDetails methods implementation
     @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Pastikan authority konsisten dengan yang digunakan di SecurityConfig
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return password; // Already have this from Lombok @Data
    }

    @Override
    public String getUsername() {
        return username; // Already have this from Lombok @Data
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Account never expires
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Account never locked
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Credentials never expire
    }

    @Override
    public boolean isEnabled() {
        return true; // Account always enabled
    }

    // Keep your existing getters and setters if you need custom logic
    // Otherwise, @Data from Lombok will handle them
}