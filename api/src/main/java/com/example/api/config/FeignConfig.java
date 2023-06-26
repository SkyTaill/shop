package com.example.api.config;


import com.example.api.category.api.CategoryGateway;
import com.example.api.manufacturer.api.ManufacturerGateway;
import com.example.api.product.api.ProductGateway;
import feign.*;
import feign.codec.ErrorDecoder;
import feign.optionals.OptionalDecoder;
import feign.slf4j.Slf4jLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.support.ResponseEntityDecoder;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.cloud.openfeign.support.SpringMvcContract;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.converter.HttpMessageConverter;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableFeignClients
@EnableConfigurationProperties(GbApiProperties.class)
@RequiredArgsConstructor
@Import(value = {FeignClientFactory.class})
public class FeignConfig {

    private final GbApiProperties gbApiProperties;
    private final FeignClientFactory feignClientFactory;

    @Bean
    public CategoryGateway categoryGateway() {
        return feignClientFactory.newFeignClient(CategoryGateway.class, gbApiProperties.getEndpoint().getCategoryUrl());
    }

    @Bean
    public ManufacturerGateway manufacturerGateway() {
        return feignClientFactory.newFeignClient(ManufacturerGateway.class, gbApiProperties.getEndpoint().getManufacturerUrl());
    }

    @Bean
    public ProductGateway productGateway() {
        return feignClientFactory.newFeignClient(ProductGateway.class, gbApiProperties.getEndpoint().getProductUrl());
    }

}