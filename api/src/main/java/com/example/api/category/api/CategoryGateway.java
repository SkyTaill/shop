package com.example.api.category.api;


import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(url="http//localhost:8080/internal/api/v1/category",name="CategoryGateway")

public interface CategoryGateway {
}
