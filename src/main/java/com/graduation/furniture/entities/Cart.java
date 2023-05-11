package com.graduation.furniture.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@JsonIgnoreProperties({"user", "cartItems"})
@Data
@NoArgsConstructor
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer cartId;

    private Integer total;

    @OneToOne
    @JoinColumn(name = "user_name")
    private Users user;

    @OneToMany(mappedBy = "cart")
    private Set<CartItem> cartItems;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate createdAt;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime updatedAt;

    private boolean deleted;
}
