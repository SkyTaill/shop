package com.example.api.product.api;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(url="http//localhost:8080/internal/api/v1/product",name="ProductGateway")

public interface ProductGateway {
}
