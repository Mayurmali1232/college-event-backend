package com.jsp.collegeevent.entity;

import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String title;

    private String description;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    private String location;

    // FIX: Changed to 'Boolean' (Object) to safely handle NULL values in the database
    @Column(name = "student_seen")
    private Boolean studentSeen = false;

    // FIX: Added @JsonIgnoreProperties to break the circular JSON reference/infinite loop
    @ManyToOne
    @JsonIgnoreProperties("events")
    private User coordinator;

    @Column(name = "image_url")
    private String imageUrl;

    public Event() {}

    public Event(Long id, @NotBlank String title, String description, @NotNull LocalDate date, String location, User coordinator) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.date = date;
        this.location = location;
        this.coordinator = coordinator;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public User getCoordinator() { return coordinator; }
    public void setCoordinator(User coordinator) { this.coordinator = coordinator; }
    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
    public Boolean getStudentSeen() { return studentSeen; }
    public void setStudentSeen(Boolean studentSeen) { this.studentSeen = studentSeen; }
}