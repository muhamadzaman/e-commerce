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
    private MyMapper myMapper;
    public ProductController(ProductService productService, MyMapper myMapper)
    {
        this.productService = productService;
        this.myMapper = myMapper;
    }

    @PostMapping
    private ResponseEntity<Product> saveProduct(@Valid @RequestBody ProductDto productDto)
    {
        Product product = myMapper.productDtoToProduct(productDto);
        productService.createProduct(product);
        return ResponseEntity.status(HttpStatus.OK).body(product);
    }
    @GetMapping
    private ResponseEntity<List<ProductDto>> getAllProducts()
    {
        List<ProductDto> allProducts = productService
                .readAllProducts()
                .stream().map(product -> myMapper.productToProductDto(product))
                .collect(Collectors.toList());

        return ResponseEntity.ok(allProducts);
    }
    @GetMapping("/{id}") ResponseEntity<ProductDto> getProductById(@PathVariable String id)
    {
        Product product = productService.readProductById(id);
        ProductDto productDto = myMapper.productToProductDto(product);
        return ResponseEntity.ok(productDto);
    }
    @PutMapping("/{id}/update")
    private ResponseEntity<Product> updateProduct(@PathVariable String id, @RequestBody ProductDto productDto)
    {
        Product product = myMapper.productDtoToProduct(productDto);
        product = productService.updateProductById(id, product);
        return ResponseEntity.ok(product);
    }

    @DeleteMapping("/{id}/delete")
    private void deleteProduct(@PathVariable String id)
    { productService.deleteProductById(id); }

}
