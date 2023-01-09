package com.services.productservice.implementations.services;

import com.services.productservice.dtos.ProductDto;
import com.services.productservice.entities.Comment;
import com.services.productservice.entities.Product;
import com.services.productservice.external.services.CommentService;
import com.services.productservice.mappers.MyMapper;
import com.services.productservice.repositories.ProductRepository;
import com.services.productservice.services.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductServiceImplementation implements ProductService
{
    private ProductRepository productRepository;
    private MyMapper myMapper;
    private CommentService commentService;
    public ProductServiceImplementation
            (ProductRepository productRepository, MyMapper myMapper, CommentService commentService)
    {
        this.productRepository = productRepository;
        this.myMapper = myMapper;
        this.commentService = commentService;
    }

    @Override
    public ProductDto createProduct(ProductDto productDto)
    {
        String id = UUID.randomUUID().toString();
        Product product = myMapper.productDtoToProduct(productDto);
        product.setId(id);
        productRepository.save(product);
        return myMapper.productToProductDto(product);
    }
    @Override
    public List<ProductDto> readAllProducts()
    {
        List<ProductDto> allProducts = productRepository.
                findAll()
                .stream()
                .map(product ->
                {
                    List<Comment> comments = commentService.getComments(product.getId());
                    ProductDto productDto = myMapper.productToProductDto(product);
                    productDto.setComments(comments);
                    return productDto;
                }
                )
                .collect(Collectors.toList());

        return allProducts;
    }
    @Override
    public ProductDto readProductById(String id)
    {
        Product product = findProduct(id);
        return myMapper.productToProductDto(product);
    }
    @Override
    public ProductDto updateProductById(String id, ProductDto productDto)
    {
        Product oldProduct = findProduct(id);
        Product newProduct = myMapper.productDtoToProduct(productDto);
        newProduct.setId(oldProduct.getId());
        productRepository.save(newProduct);
        return myMapper.productToProductDto(newProduct);
    }

    @Override
    public List<ProductDto> getAllByUserId(Long userId) {
        return productRepository
                .findAllByUserId(userId)
                .stream()
                .map(product -> myMapper.productToProductDto(product))
                .collect(Collectors.toList());
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
