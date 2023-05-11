package com.graduation.furniture.controller;

import com.graduation.furniture.dto.FeedbackDTO;
import com.graduation.furniture.entities.Feedback;
import com.graduation.furniture.entities.Product;
import com.graduation.furniture.entities.Users;
import com.graduation.furniture.service.FeedbackService;
import com.graduation.furniture.service.ProductService;
import com.graduation.furniture.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @GetMapping("")
    public ResponseEntity<Page<Feedback>> findAll(@RequestParam(name = "page", defaultValue = "1") int page, @RequestParam(name = "size", defaultValue = "5") int size) {
        Page<Feedback> feedbackPage = feedbackService.findAll(page, size);
        if (feedbackPage.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(feedbackPage);
        }
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody FeedbackDTO feedbackDTO, BindingResult bindingResult) {
        Integer idUpdate = null;
        try {
            idUpdate = Integer.parseInt(id);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
        new FeedbackDTO().validate(feedbackDTO, bindingResult);
        Users user = userService.findById(feedbackDTO.getUser().getUserName()).orElse(null);
        Product product = productService.findById(feedbackDTO.getProduct().getProductId()).orElse(null);
        if (user == null) {
            bindingResult.rejectValue("user", "", "User is not exist!!");
        }
        if (product == null) {
            bindingResult.rejectValue("product", "", "Product is not exist");
        }
        Feedback feedbackUpdate = feedbackService.findById(idUpdate).orElse(null);
        if (feedbackUpdate == null) {
            return ResponseEntity.notFound().build();
        }
        if (bindingResult.hasFieldErrors()) {
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            return new ResponseEntity<>(fieldErrors, HttpStatus.BAD_REQUEST);
        }
        Feedback feedback = new Feedback();
        feedbackDTO.setFeedbackId(feedbackUpdate.getFeedbackId());
        BeanUtils.copyProperties(feedbackDTO, feedback);
        Feedback newFeedback = feedbackService.update(feedback);
        return ResponseEntity.ok(newFeedback);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Feedback> findById(@PathVariable String id) {
        Integer idFind = null;
        try {
            idFind = Integer.parseInt(id);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
        Feedback feedback = feedbackService.findById(idFind).orElse(null);
        if (feedback == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(feedback);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Feedback> deleteById(@PathVariable String id) {

        Integer idDelete = null;
        try {
            idDelete = Integer.parseInt(id);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
        Feedback feedback = feedbackService.findById(idDelete).orElse(null);

        if (feedback == null) {
            return ResponseEntity.notFound().build();
        }
        feedbackService.deleteById(idDelete);
        return ResponseEntity.ok(feedback);
    }
}
