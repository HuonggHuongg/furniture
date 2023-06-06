package com.graduation.furniture.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Data
@NoArgsConstructor
public class ForgotPassDTO implements Validator {
    private String email;

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        ForgotPassDTO forgotPassDTO = (ForgotPassDTO) target;
        if ("".equals(forgotPassDTO.email)) {
            errors.rejectValue("email", "", "Email is required!!");
        } else if (!forgotPassDTO.email.matches("^[a-zA-Z0-9.]{2,32}@[a-z]{2,12}\\.[a-z]{2,12}$")) {
            errors.rejectValue("email", "", "Email is invalid!!");
        }
    }
}
