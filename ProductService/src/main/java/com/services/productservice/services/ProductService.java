package com.services.productservice.services;

import com.services.productservice.dtos.ProductDto;
import com.services.productservice.entities.Product;

import java.util.List;

public interface ProductService
{
    ProductDto createProduct(ProductDto productDto);
    List<ProductDto> readAllProducts();
        ProductDto readProductById(String id);
    ProductDto updateProductById(String id, ProductDto productDto);

    List<ProductDto> getAllByUserId(Long userId);
    void deleteProductById(String id);
}
