package com.services.cartservice.services;

import com.services.cartservice.dtos.GetCartDto;
import com.services.cartservice.dtos.PostCartDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public interface SessionService
{
    PostCartDto saveCartProductsToSession(HttpServletRequest request, PostCartDto postCartDTO);
    GetCartDto getAllCartProducts(HttpSession httpSession);
    void deleteCartProductsFromSession(HttpServletRequest request);
    Long updateQuantiy(PostCartDto postCartDTO,HttpSession httpSession);
}
