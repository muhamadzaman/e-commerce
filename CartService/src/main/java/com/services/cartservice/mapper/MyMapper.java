package com.services.cartservice.mapper;

import com.services.cartservice.dtos.GetCartDto;
import com.services.cartservice.entitites.Cart;
import com.services.cartservice.entitites.CartProducts;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MyMapper
{
    private ModelMapper modelMapper;
    public MyMapper(ModelMapper modelMapper)
    { this.modelMapper = modelMapper; }

    public GetCartDto cartToCartDto(Cart cart, List<CartProducts> cartProducts)
    {
        GetCartDto getCartDto;
        getCartDto = modelMapper.map(cart, GetCartDto.class);
        getCartDto.setCartProducts(cartProducts);
        return getCartDto;
    }
}
