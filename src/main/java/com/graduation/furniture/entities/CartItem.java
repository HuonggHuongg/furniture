package com.graduation.furniture.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@JsonIgnoreProperties({"product"})
@Data
@NoArgsConstructor
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cartItemId;

    private Integer quantity;

    private Integer paymentCartItem;

    @ManyToOne
    @JoinColumn(name = "product_id" )
    private Product product;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate createdAt;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    private boolean deleted;
}
