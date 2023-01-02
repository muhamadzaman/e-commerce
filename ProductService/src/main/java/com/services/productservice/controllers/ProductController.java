package com.services.productservice.controllers;

import com.services.productservice.dtos.ProductDto;
import com.services.productservice.entities.Product;
import com.services.productservice.mappers.MyMapper;
import com.services.productservice.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
public class ProductController
{
    private ProductService productService;
    public ProductController(ProductService productService)
    { this.productService = productService; }

    @PostMapping
    private ResponseEntity<ProductDto> saveProduct(@Valid @RequestBody ProductDto productDto)
    {
        ProductDto savedProduct = productService.createProduct(productDto);
        return ResponseEntity.status(HttpStatus.OK).body(savedProduct);
    }
    @GetMapping
    private ResponseEntity<List<ProductDto>> getAllProducts()
    {
        List<ProductDto> allProducts = productService.readAllProducts();
        return ResponseEntity.ok(allProducts);
    }
    @GetMapping("/{id}") ResponseEntity<ProductDto> getProductById(@PathVariable String id)
    {
        ProductDto productDto = productService.readProductById(id);
        return ResponseEntity.ok(productDto);
    }
    @PutMapping("/{id}/update")
    private ResponseEntity<ProductDto> updateProduct(@PathVariable String id, @RequestBody ProductDto productDto)
    {
        ProductDto savedProduct = productService.updateProductById(id, productDto);
        return ResponseEntity.ok(savedProduct);
    }

    @DeleteMapping("/{id}/delete")
    private void deleteProduct(@PathVariable String id)
    { productService.deleteProductById(id); }

}
