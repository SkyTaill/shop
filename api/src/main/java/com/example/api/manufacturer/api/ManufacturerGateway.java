package com.example.api.manufacturer.api;

import com.example.api.manufacturer.dto.ManufacturerDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@FeignClient(url="http//localhost:8080/internal/api/v1/manufacturer",name="ManufacturerGateway")
public interface ManufacturerGateway {
    @GetMapping
    public List<ManufacturerDto> getManufacturerList();

    @GetMapping("/{manufacturerId}")
    public ResponseEntity<ManufacturerDto> getManufacturer(@PathVariable("manufacturerId") Long id) ;

    @PostMapping
    public ResponseEntity<?> handlePost(@Validated @RequestBody ManufacturerDto manufacturerDto);

    @PutMapping("/{manufacturerId}")
    public ResponseEntity<ManufacturerDto> handleUpdate(@PathVariable("manufacturerId") Long id, @Validated @RequestBody ManufacturerDto manufacturerDto) ;

    @DeleteMapping("/{manufacturerId}")
    public void deleteById(@PathVariable("manufacturerId") Long id);

}
