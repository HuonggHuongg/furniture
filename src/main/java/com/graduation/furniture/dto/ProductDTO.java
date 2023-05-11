package com.graduation.furniture.dto;

import com.graduation.furniture.entities.CartItem;
import com.graduation.furniture.entities.Category;
import com.graduation.furniture.entities.Feedback;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
public class ProductDTO implements Validator {
    private Integer productId;

    private String productName;

    private String description;

    private Integer price;

    private String image;

    private Integer inventoryQuantity;

    private LocalDate createdAt;

    private LocalDateTime updatedAt;

    private Category category;

    private Set<Feedback> feedbacks;

    private CartItem cartItem;

    private boolean isActivity;

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        ProductDTO productDTO = (ProductDTO) target;

        if ("".equals(productDTO.productName)){
            errors.rejectValue("productName", "", "Product name is required!!");
        }else if(productDTO.productName.length() > 255) {
            errors.rejectValue("productName", "", "Product name must less than 255 character!!");
        }

        if (productDTO.price == null){
            errors.rejectValue("price", "", "Price is required!!");
        }else if(productDTO.price <= 0) {
            errors.rejectValue("price", "", "Price is invalid!!");
        }

        if ("".equals(productDTO.description)){
            errors.rejectValue("description", "", "Description is required!!");
        }else if(productDTO.productName.length() > 255) {
            errors.rejectValue("description", "", "Description must less than 255 character!!");
        }

        if (productDTO.inventoryQuantity == null){
            errors.rejectValue("inventoryQuantity", "", "InventoryQuantity is required!!");
        }else if(productDTO.inventoryQuantity <= 0) {
            errors.rejectValue("inventoryQuantity", "", "InventoryQuantity is invalid!!");
        }
    }
}
