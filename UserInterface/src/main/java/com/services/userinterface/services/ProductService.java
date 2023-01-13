package com.services.userinterface.services;

import com.services.userinterface.dtos.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "API-GATEWAY")
public interface ProductService
{
    @PostMapping("/products")
    ProductDto createProduct(@RequestBody ProductDto productDto);

    @GetMapping("/products")
    List<ProductDto> readAllProducts();

    @GetMapping("products/{id}")
    ProductDto getProductById(@PathVariable String id);

    @GetMapping("products/users/{userId}")
    List<ProductDto> getAllByUserId(Long userId);

    @PutMapping("products/{id}/update")
    ProductDto updateProductById(@PathVariable String id,@RequestBody ProductDto productDto);

    @DeleteMapping("products/{id}/delete")
    void deleteProductById(@PathVariable String id);
}
