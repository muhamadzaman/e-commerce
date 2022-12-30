package com.services.productservice.services;

import com.services.productservice.entities.Product;

import java.util.List;

public interface ProductService
{
    Product createProduct(Product product);
    List<Product> readAllProducts();
    Product readProductById(String id);
    Product updateProductById(String id, Product product);
    void deleteProductById(String id);
}
