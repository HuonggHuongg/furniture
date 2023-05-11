package com.graduation.furniture.dto;

import com.graduation.furniture.entities.Product;
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
public class CategoryDTO implements Validator {

    private Integer categoryId;

    private String categoryName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate createdAt;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime updatedAt;

    private Set<Product> products;

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        CategoryDTO categoryDTO = (CategoryDTO) target;

        if ("".equals(categoryDTO.categoryName)){
            errors.rejectValue("categoryName", "", "Category name is required!!");
        }else if(categoryDTO.categoryName.length() > 255) {
            errors.rejectValue("categoryName", "", "Category name must less than 255 character!!");
        }
     }
}
