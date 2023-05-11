package com.graduation.furniture.dto;

import com.graduation.furniture.entities.Product;
import com.graduation.furniture.entities.Users;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class FeedbackDTO implements Validator {

    private Integer feedbackId;

    private String commentText;

    private Integer rating;

    private Users user;

    private Product product;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate createdAt;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime updatedAt;

    private boolean isActivity;

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        FeedbackDTO feedbackDTO = (FeedbackDTO) target;

        if ("".equals(feedbackDTO.commentText)){
            errors.rejectValue("commentText", "", "CommentText is required!!");
        }else if(feedbackDTO.commentText.length() > 255) {
            errors.rejectValue("commentText", "", "CommentText must less than 255 character!!");
        }

        if (feedbackDTO.rating == null){
            errors.rejectValue("rating", "", "Rating is required!!");
        }else if(feedbackDTO.rating <= 0 || feedbackDTO.rating > 5) {
            errors.rejectValue("rating", "", "Rating must from 1 to 5!!");
        }
    }
}
