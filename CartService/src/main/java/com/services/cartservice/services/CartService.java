package com.services.cartservice.services;

import com.services.cartservice.dtos.GetCartDto;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

public interface CartService
{
    GetCartDto getCartByUserId(Long userId);
    GetCartDto saveSessionFromCartProductsToDatabase(HttpSession httpSession, Long userId);
}
