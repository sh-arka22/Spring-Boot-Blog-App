package com.springboot.blog.controller;

import com.springboot.blog.entity.Category;
import com.springboot.blog.payload.CategoryDto;
import com.springboot.blog.service.CategoryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@Tag(name = "CRUD REST APIs for Category Resource")
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    /*
     * Adding a new category in Database
     */
    @Operation(summary = "Creating a new Category by Amin", 
                description = "Only Admin can create new category")
    @ApiResponse(responseCode = "201", description = "Http Status 201 CREATED")
    @SecurityRequirement(name = "Bear Authentication")
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CategoryDto> addCategory(@RequestBody CategoryDto categoryDto) {
        CategoryDto savedCategory = categoryService.addCategory(categoryDto);
        return new ResponseEntity<>(savedCategory, HttpStatus.CREATED);
    }









    /*
     * getting the category 
     */
    @Operation(summary = "Getting the category", 
                description = "With the category i get all categories along with the post in that respose body")
    @ApiResponse(responseCode = "200", description = "Http Status 200 SUCCESS")
    // @SecurityRequirement(name = "Bear Authentication")
    @GetMapping("{id}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable("id") Long categoryId) {
        CategoryDto categoryDto = categoryService.getCategory(categoryId);
        return ResponseEntity.ok(categoryDto);
    }




    




    @Operation(summary = "Getting all the Category", 
                description = "getting all the categories")
    @ApiResponse(responseCode = "200", description = "Http Status 200 SUCCESS")
    // @SecurityRequirement(name = "Bear Authentication")
    // Build Get All Categories REST API
    @GetMapping
    public ResponseEntity<List<CategoryDto>> getCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }






    @Operation(summary = "Updating a Category only by admin", 
                description = "Only ADMIN with TOKEN can update th ecategory")
    @ApiResponse(responseCode = "200", description = "Http Status 200 SUCCESS")
    @SecurityRequirement(name = "Bear Authentication")
    // Build Update Category REST API
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("{id}")
    public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto categoryDto,
            @PathVariable("id") Long categoryId) {
        return ResponseEntity.ok(categoryService.updateCategory(categoryDto, categoryId));
    }

    




    @Operation(summary = "Deleting a Category only by admin", 
                description = "Only ADMIN with TOKEN can DELETE the category")
    @ApiResponse(responseCode = "200", description = "Http Status 200 SUCCESS")
    @SecurityRequirement(name = "Bear Authentication")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable("id") Long categoryId) {
        categoryService.deleteCategory(categoryId);
        return ResponseEntity.ok("Category deleted successfully!.");
    }
}
