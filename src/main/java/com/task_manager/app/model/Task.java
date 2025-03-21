package com.task_manager.app.model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor


public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;

    @Enumerated(EnumType.STRING)
    private Priority priority;

    @Enumerated(EnumType.STRING)
    private Status status;

    private String comments;

    @ManyToOne
    private User author;

    @ManyToOne
    private User assignee;

    @ManyToOne
    private User executor;

    public enum Priority {HIGH, MEDIUM, LOW}
    public enum Status {PENDING, IN_PROGRESS, COMPLETE}

}
