package com.graduation.furniture.entities;

import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;

    @ManyToOne
    @JoinColumn(name = "user_name")
    private Users user;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
}
