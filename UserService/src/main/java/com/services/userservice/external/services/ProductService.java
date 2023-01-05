package com.services.userservice.external.services;

import com.services.userservice.entities.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "PRODUCT-SERVICE")
public interface ProductService
{
    @GetMapping("/products/users/{userId}")
    List<Product> getProducts(@PathVariable long userId);
}
