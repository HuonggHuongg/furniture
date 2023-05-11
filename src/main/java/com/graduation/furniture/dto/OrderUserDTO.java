package com.graduation.furniture.dto;

import com.graduation.furniture.entities.OrderItem;
import com.graduation.furniture.entities.StatusOrder;
import com.graduation.furniture.entities.Users;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
public class OrderUserDTO implements Validator {

    private Integer orderId;

    private Users user;

    private Set<OrderItem> orderItems;

    private List<Integer> productIds;

    private String receivingAddress;

    private String fullName;

    private String phoneNumber;

    private StatusOrder statusOrder;

    private Integer totalOrder;

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {

    }
}
