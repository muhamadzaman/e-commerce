package com.services.productservice.mappers;

import com.services.productservice.dtos.ProductDto;
import com.services.productservice.entities.Product;

public interface MyMapper
{
    ProductDto productToProductDto(Product product);
    Product productDtoToProduct(ProductDto productDto);
}
