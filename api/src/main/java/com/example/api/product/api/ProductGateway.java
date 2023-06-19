package com.example.api.product.api;

import com.example.api.product.dto.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@FeignClient(url="http//localhost:8080/internal/api/v1/product",name="ProductGateway")

public interface ProductGateway {
    @GetMapping
    public List<ProductDto> getProductList();

    @GetMapping("/{productId}")
    public ResponseEntity<?> getProduct(@PathVariable("productId") Long id);

    @PostMapping
    public ResponseEntity<?> handlePost(@Validated @RequestBody ProductDto productDto) ;

    @PutMapping("/{productId}")
    public ResponseEntity<?> handleUpdate(@PathVariable("productId") Long id, @Validated @RequestBody ProductDto productDto) ;

    @DeleteMapping("/{productId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable("productId") Long id);
}
