package com.services.productservice.implementations.services;

import com.services.productservice.entities.Product;
import com.services.productservice.repositories.ProductRepository;
import com.services.productservice.services.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class ProductServiceImplementation implements ProductService
{
    private ProductRepository productRepository;
    public ProductServiceImplementation(ProductRepository productRepository)
    { this.productRepository = productRepository; }

    @Override
    public Product createProduct(Product product)
    {
        String id = UUID.randomUUID().toString();
        product.setId(id);
        return productRepository.save(product);
    }
    @Override
    public List<Product> readAllProducts() { return productRepository.findAll(); }
    @Override
    public Product readProductById(String id) { return findProduct(id); }
    @Override
    public Product updateProductById(String id, Product newProduct)
    {
        Product oldProduct = findProduct(id);
        newProduct.setId(oldProduct.getId());
        return productRepository.save(newProduct);
    }
    @Override
    public void deleteProductById(String id)
    {
        Product product = findProduct(id);
        productRepository.deleteById(product.getId());
    }
    private Product findProduct(String id)
    {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException
                        ("Product with given id is not found on the server. Requested id = " + id));

        return product;
    }
}
