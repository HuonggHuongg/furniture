package com.graduation.furniture.dto;

import com.graduation.furniture.entities.Cart;
import com.graduation.furniture.entities.Product;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class CartItemDTO implements Validator {

    private Integer cartItemId;

    private Integer quantity;

    private Integer productId;

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {

    }
}
