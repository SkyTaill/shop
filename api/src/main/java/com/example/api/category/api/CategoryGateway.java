package com.example.api.category.api;


import com.example.api.category.dto.CategoryDto;
import com.example.api.product.dto.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(url="http//localhost:8080/internal/api/v1/category",name="CategoryGateway")

public interface CategoryGateway {
    @GetMapping
    List<CategoryDto> getCategoryList();

    @GetMapping("/{id}")
    ResponseEntity<CategoryDto> getCategory(@PathVariable("id") Long id);

    @PostMapping
    ResponseEntity<CategoryDto> handlePost(@Validated @RequestBody CategoryDto categoryDto);

    @PutMapping("/{id}")
    ResponseEntity<CategoryDto> handleUpdate(@PathVariable("id") Long id,
                                             @Validated @RequestBody CategoryDto categoryDto);

    @DeleteMapping("/{id}")
    void deleteById(@PathVariable("id") Long id);
}
