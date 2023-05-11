package com.graduation.furniture.entities;

import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer feedbackId;

    @Column(columnDefinition = "varchar(255)")
    private String commentText;

    private Integer rating;

    @ManyToOne
    @JoinColumn(name = "user_name")
    private Users user;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate createdAt;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime updatedAt;

    private boolean deleted;
}
