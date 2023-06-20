package com.example.api.config;

import com.example.api.category.api.CategoryGateway;
import com.example.api.manufacturer.api.ManufacturerGateway;
import com.example.api.product.api.ProductGateway;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(clients = {
CategoryGateway.class,
 ManufacturerGateway.class,
        ProductGateway.class
})
public class FeignConfig {


}
