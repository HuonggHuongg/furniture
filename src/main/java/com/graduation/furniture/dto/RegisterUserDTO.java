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
public class RegisterUserDTO implements Validator {
    private String userName;

    private String firstName;

    private String lastName;

    private String avatar;

    private String password;

    private String email;

    private String phoneNumber;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate createdAt;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime updatedAt;

    private Set<UserRole> userRoles;

    private Set<Feedback> feedbacks;

    private Cart cart;

    private Set<OrderUser> orderUsers;

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        RegisterUserDTO registerUserDTO = (RegisterUserDTO) target;

        if ("".equals(registerUserDTO.userName)) {
            errors.rejectValue("userName", "", "Username is required!!");
        } else if (!registerUserDTO.userName.matches("^[a-zA-Z0-9\\S]{2,30}$")) {
            errors.rejectValue("userName", "", "Username must from 2 to 30 character and no spaces!!");
        }

        if ("".equals(registerUserDTO.email)) {
            errors.rejectValue("email", "", "Email is required!!");
        } else if (!registerUserDTO.email.matches("^[a-zA-Z0-9.]{2,32}@[a-z]{2,12}\\.[a-z]{2,12}$")) {
            errors.rejectValue("email", "", "Email is invalid!!");
        }

        if ("".equals(registerUserDTO.password)) {
            errors.rejectValue("password", "", "Password is required!!");
        } else if (registerUserDTO.password.length() < 8 || registerUserDTO.password.length() > 32) {
            errors.rejectValue("password", "", "Password must from 8 to 32 character!!");
        }

        if ("".equals(registerUserDTO.phoneNumber)) {
            errors.rejectValue("phoneNumber", "", "Phone number is required!!");
        } else if (!registerUserDTO.phoneNumber.matches("^0[0-9]{9}$")) {
            errors.rejectValue("phoneNumber", "", "Phone number is invalid!!");
        }
    }
}
