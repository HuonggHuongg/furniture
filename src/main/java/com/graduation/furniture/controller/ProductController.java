package com.graduation.furniture.controller;

import com.graduation.furniture.dto.FeedbackDTO;
import com.graduation.furniture.dto.ProductDTO;
import com.graduation.furniture.dto.TopProductTrendingDto;
import com.graduation.furniture.entities.*;
import com.graduation.furniture.service.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/product")

public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private FeedbackService feedbackService;

    @Autowired
    private UserService userService;

    @Autowired
    private ReportService reportService;

    @GetMapping("")
    public ResponseEntity<Page<Product>> findAll(@RequestParam(name = "page", defaultValue = "1") int page,
                                                 @RequestParam(name = "size", defaultValue = "5") int size,
                                                 @RequestParam(name = "search", defaultValue = "") String search,
                                                 @RequestParam(name = "filter", defaultValue = "") String filter,
                                                 @RequestParam(name = "order", defaultValue = "") String order,
                                                 @RequestParam(name = "dir", defaultValue = "") String dir) {
        Page<Product> productPage = productService.findAll(page, size, search, filter, order, dir);
        if (productPage.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(productPage);
        }
    }

    @GetMapping("findAll")
    public ResponseEntity<List<Product>> findAllProduct() {
        List<Product> productPage = productService.findAll();
        if (productPage.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(productPage);
        }
    }

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Secured("ROLE_ADMIN")
    public ResponseEntity<?> add(@RequestBody ProductDTO productDTO, BindingResult bindingResult) {
        new ProductDTO().validate(productDTO, bindingResult);
        Category category = categoryService.findById(productDTO.getCategory().getCategoryId()).orElse(null);
        if (category == null){
            bindingResult.rejectValue("category", "", "Category is not exist!!");
        }
        if (bindingResult.hasFieldErrors()) {
            return new ResponseEntity<>(bindingResult.getFieldErrors(), HttpStatus.BAD_REQUEST);
        }
        Product product = new Product();
        BeanUtils.copyProperties(productDTO, product);
        Product newCategory = productService.save(product);
        return new ResponseEntity<>(newCategory, HttpStatus.OK);
    }

    @PatchMapping(value = "/{id}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody ProductDTO productDTO, BindingResult bindingResult) {
        Integer idUpdate = null;
        try {
            idUpdate = Integer.parseInt(id);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
        new ProductDTO().validate(productDTO, bindingResult);
        Category category = categoryService.findById(productDTO.getCategory().getCategoryId()).orElse(null);
        if (category == null){
            bindingResult.rejectValue("category", "", "Category is not exist!!");
        }
        Product productUpdate = productService.findById(idUpdate).orElse(null);
        if (productUpdate == null) {
            return ResponseEntity.notFound().build();
        }
        if (bindingResult.hasFieldErrors()) {
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            return new ResponseEntity<>(fieldErrors, HttpStatus.BAD_REQUEST);
        }
        Product product = new Product();
        productDTO.setProductId(productUpdate.getProductId());
        productDTO.setCreatedAt(productUpdate.getCreatedAt());
        BeanUtils.copyProperties(productDTO, product);
        Product newCategory = productService.update(product);
        return ResponseEntity.ok(newCategory);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@PathVariable Integer id) {
        Product product = productService.findById(id).orElse(null);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String id) {

        Integer idDelete = null;
        try {
            idDelete = Integer.parseInt(id);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
        Product product = productService.findById(idDelete).orElse(null);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        productService.deleteById(idDelete);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/feedback")
    public ResponseEntity<List<Feedback>> getFeedback(@PathVariable Integer id) {
        Product product = productService.findById(id).orElse(null);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        List<Feedback> feedbacks = feedbackService.findByProduct_ProductId(id);
        return ResponseEntity.ok(feedbacks);
    }

    @PostMapping(value = "/{id}/feedback", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addFeedback(Authentication currentUser, @PathVariable String id, @RequestBody FeedbackDTO feedbackDTO, BindingResult bindingResult) {
        Integer idProductFeedback = null;
        try {
            idProductFeedback = Integer.parseInt(id);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
//        if (!feedbackDTO.getUser().getUserName().equals(currentUser.getName())) {
//            return new ResponseEntity<>("You do not have permission to edit this user's shopping cart information", HttpStatus.UNAUTHORIZED);
//        }
        new FeedbackDTO().validate(feedbackDTO, bindingResult);

        Users user = userService.findById(currentUser.getName()).orElse(null);

        System.err.println(user.getUserName());
        Product product = productService.findById(idProductFeedback).orElse(null);
        if (user == null) {
            bindingResult.rejectValue("user", "", "User is not exist!!");
        }
        if (product == null) {
            bindingResult.rejectValue("product", "", "Product is not exist");
        }
        if (bindingResult.hasFieldErrors()) {
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            return new ResponseEntity<>(fieldErrors, HttpStatus.BAD_REQUEST);
        }
        Feedback feedback = new Feedback();

        feedbackDTO.setProduct(product);
        BeanUtils.copyProperties(feedbackDTO, feedback);
        feedback.setUser(user);

        Feedback newFeedback = feedbackService.save(feedback);
        return new ResponseEntity<>(newFeedback, HttpStatus.OK);
    }

    @GetMapping("trending-product")
    public ResponseEntity<?> trendingProductByPeriodTime(@RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
                                                         @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {

        List<TopProductTrendingDto> topProductTrendingDtoList = reportService.trendingProductByPeriod(startDate, endDate);
        return new ResponseEntity<>(topProductTrendingDtoList, HttpStatus.OK);
    }
    @GetMapping("/category/{id}")
    public ResponseEntity<?> getProductByCategory(@PathVariable Integer id) {
        Category category = categoryService.findById(id).orElse(null);
        if(category ==  null){
            return ResponseEntity.noContent().build();
        }

        List<Product> products = productService.findProductByCategoryId(id);
        if(products.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(products);
    }
}
