package com.graduation.furniture.dto;

import com.graduation.furniture.entities.Cart;
import com.graduation.furniture.entities.Feedback;
import com.graduation.furniture.entities.OrderUser;
import com.graduation.furniture.entities.UserRole;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
public class LoginDTO implements Validator {
    private String userName;
    private String password;

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        LoginDTO loginDTO = (LoginDTO) target;
        if ("".equals(loginDTO.userName)) {
            errors.rejectValue("userName", "", "Username is required!!");
        } else if (!loginDTO.userName.matches("^[a-zA-Z0-9\\S]{2,30}$")) {
            errors.rejectValue("userName", "", "Username must from 2 to 30 character and no spaces!!");
        }

        if ("".equals(loginDTO.password)) {
            errors.rejectValue("password", "", "Password is required!!");
        } else if (loginDTO.password.length() < 8 || loginDTO.password.length() > 32) {
            errors.rejectValue("password", "", "Password must from 8 to 32 character!!");
        }
    }
}
