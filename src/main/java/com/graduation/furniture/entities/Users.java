package com.graduation.furniture.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@JsonIgnoreProperties({"userRoles", "feedbacks", "cart", "orderUsers"})
@Data
@NoArgsConstructor
@SuperBuilder
public class Users {
    @Id
    @Column(columnDefinition = "varchar(255)")
    private String userName;
    
    @Column(columnDefinition = "varchar(255)")
    private String firstName;

    @Column(columnDefinition = "varchar(255)")
    private String lastName;

    @Column(columnDefinition = "varchar(1000)")
    private  String avatar;

    @Column(columnDefinition = "varchar(555)")
    private String password;

    @Column(columnDefinition = "varchar(255)")
    private String email;

    @Column(columnDefinition = "varchar(255)")
    private String address;

    @Column(columnDefinition = "varchar(255)")
    private String phoneNumber;

    private String resetPassToken;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate createdAt;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "user")
    private List<UserRole> userRoles;

    @OneToMany(mappedBy = "user")
    private Set<Feedback> feedbacks;

    @OneToOne(mappedBy = "user")
    private Cart cart;

    @OneToMany(mappedBy ="user")
    private Set<OrderUser> orderUsers;

    private boolean deleted;
}
