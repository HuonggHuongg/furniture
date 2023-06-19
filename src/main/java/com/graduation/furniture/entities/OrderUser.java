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
@Data
@NoArgsConstructor
@JsonIgnoreProperties({"orderItems"})
public class OrderUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderId;

    @ManyToOne
    @JoinColumn(name = "user_name")
    private Users user;

    private boolean paymentStatus;

    @Column(columnDefinition = "varchar(255)")
    private String receivingAddress;

    @Column(columnDefinition = "varchar(255)")
    private String fullName;

    @Column(columnDefinition = "varchar(255)")
    private String phoneNumber;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private  StatusOrder statusOrder;

    @OneToMany(mappedBy = "orderUser")
    private Set<OrderItem> orderItems;

    private Integer totalOrder;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime createdAt;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime updatedAt;

    private boolean deleted;
}
