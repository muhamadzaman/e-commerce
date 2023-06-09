package com.services.productservice.implementations.mappers;

import com.services.productservice.dtos.ProductDto;
import com.services.productservice.entities.Product;
import com.services.productservice.mappers.MyMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class MyMapperImplementation implements MyMapper
{
    private ModelMapper modelMapper;
    public MyMapperImplementation(ModelMapper modelMapper)
    { this.modelMapper = modelMapper; }

    @Override
    public ProductDto productToProductDto(Product product)
    {
        ProductDto productDto = modelMapper.map(product, ProductDto.class);
        return productDto;
    }

    @Override
    public Product productDtoToProduct(ProductDto productDto)
    {
        Product product = modelMapper.map(productDto, Product.class);
        return product;
    }
}
