package com.graduation.furniture.controller;

import com.graduation.furniture.dto.CategoryDTO;
import com.graduation.furniture.entities.Category;
import com.graduation.furniture.service.CategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@Secured("ROLE_USER")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

//    @GetMapping("")
//    public ResponseEntity<Page<Category>> findAll(@RequestParam(name = "page", defaultValue = "1") int page,
//                                                  @RequestParam(name = "size", defaultValue = "5") int size){
//        Page<Category> categoryPage = categoryService.findAll(page, size);
//
//        if(categoryPage.isEmpty()){
//            return ResponseEntity.noContent().build();
//        }else {
//            return ResponseEntity.ok(categoryPage);
//        }
//    }

    @GetMapping("")
    public ResponseEntity<List<Category>> findAll() {
        List<Category> categoryPage = categoryService.findAll();

        if (categoryPage.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(categoryPage);
        }
    }

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Secured("ROLE_ADMIN")
    public ResponseEntity<?> addCategory(@RequestBody CategoryDTO categoryDTO, BindingResult bindingResult) {
        new CategoryDTO().validate(categoryDTO, bindingResult);
        if (bindingResult.hasFieldErrors()) {
            return new ResponseEntity<>(bindingResult.getFieldErrors(), HttpStatus.BAD_REQUEST);
        }
        Category category = new Category();
        BeanUtils.copyProperties(categoryDTO, category);
        Category newCategory = categoryService.save(category);
        return new ResponseEntity<>(newCategory, HttpStatus.OK);
    }

    @PatchMapping(value = "/{id}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<?> updateCategory(@PathVariable String id, @RequestBody CategoryDTO categoryDTO, BindingResult bindingResult) {
        Integer categoryId = null;
        try {
            categoryId = Integer.parseInt(id);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
        new CategoryDTO().validate(categoryDTO, bindingResult);
        Category categoryUpdate = categoryService.findById(categoryId).orElse(null);
        if (categoryUpdate == null) {
            return ResponseEntity.notFound().build();
        }

        if (bindingResult.hasFieldErrors()) {
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            return new ResponseEntity<>(fieldErrors, HttpStatus.BAD_REQUEST);
        }
        Category category = new Category();
        categoryDTO.setCategoryId(categoryUpdate.getCategoryId());
        BeanUtils.copyProperties(categoryDTO, category);
        Category newCategory = categoryService.save(category);
        return ResponseEntity.ok(newCategory);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<Category> findById(@PathVariable Integer categoryId) {
        Category category = categoryService.findById(categoryId).orElse(null);
        if (category == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(category);
    }

    @DeleteMapping("/{id}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<Void> deleteById(@PathVariable String id) {

        Integer categoryId = null;
        try {
            categoryId = Integer.parseInt(id);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
        Category category = categoryService.findById(categoryId).orElse(null);

        if (category == null) {
            return ResponseEntity.notFound().build();
        }
        categoryService.deleteById(categoryId);
        return ResponseEntity.ok().build();
    }
}
