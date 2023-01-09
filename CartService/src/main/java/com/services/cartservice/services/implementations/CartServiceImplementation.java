package com.services.cartservice.services.implementations;

import com.services.cartservice.dtos.GetCartDto;
import com.services.cartservice.entitites.Cart;
import com.services.cartservice.entitites.CartProducts;
import com.services.cartservice.mapper.MyMapper;
import com.services.cartservice.repositories.CartProductsRepository;
import com.services.cartservice.repositories.CartRepository;
import com.services.cartservice.services.CartService;
import com.services.cartservice.services.SessionService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class CartServiceImplementation implements CartService
{
    private CartRepository cartRepository;
    private CartProductsRepository cartProductsRepository;
    private MyMapper myMapper;
    private SessionService sessionService;

    public CartServiceImplementation
            (CartRepository cartRepository,
             CartProductsRepository cartProductsRepository,
             MyMapper myMapper,
             SessionService sessionService)
    {
        this.cartRepository = cartRepository;
        this.cartProductsRepository = cartProductsRepository;
        this.myMapper = myMapper;
        this.sessionService = sessionService;
    }

    @Override
    public GetCartDto getCartByUserId(Long userId)
    {
        Cart cart = cartRepository.findByUserId(userId);
        List<CartProducts> cartProducts = cartProductsRepository.findByCartId(cart.getId());
        return myMapper.cartToCartDto(cart, cartProducts);
    }

    @Override
    public GetCartDto saveSessionFromCartProductsToDatabase(HttpSession httpSession, Long userId)
    {
        GetCartDto cartDTO = sessionService.getAllCartProducts(httpSession);
        Cart cart = new Cart(cartDTO.getId(),userId);
        Cart saveCart = cartRepository.save(cart);
        List<CartProducts> cartProducts = cartDTO.getCartProducts();
        cartProductsRepository.saveAll(cartProducts);
        return myMapper.cartToCartDto(saveCart,cartProducts);
    }
}
