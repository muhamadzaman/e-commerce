package com.services.cartservice.services.implementations;

import com.services.cartservice.dtos.GetCartDto;
import com.services.cartservice.dtos.PostCartDto;
import com.services.cartservice.entitites.Cart;
import com.services.cartservice.entitites.CartProducts;
import com.services.cartservice.mapper.MyMapper;
import com.services.cartservice.repositories.CartProductsRepository;
import com.services.cartservice.repositories.CartRepository;
import com.services.cartservice.services.SessionService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SessionServiceImplementation implements SessionService
{
    private MyMapper myMapper;
    private final CartProductsRepository cartProductsRepository;
    private final CartRepository cartRepository;

    public SessionServiceImplementation(MyMapper myMapper,
                                        CartProductsRepository cartProductsRepository,
                                        CartRepository cartRepository)
    { this.myMapper = myMapper;
        this.cartProductsRepository = cartProductsRepository;
        this.cartRepository = cartRepository;
    }

    @Override
    public PostCartDto saveCartProductsToSession(HttpServletRequest request, PostCartDto postCartDto)
    {
        HttpSession session = request.getSession();
        List<CartProducts> cartProducts = getCartProductsFromSession(session);

        Optional<CartProducts> firstProduct = cartProducts
                .stream()
                .filter(cartProduct -> cartProduct.getProductId()
                        .equals(postCartDto.getProductId()))
                .findFirst();

        if (firstProduct.isPresent())
        {
            firstProduct.get().setQuantity(firstProduct.get().getQuantity()+ postCartDto.getQuantity());
            session.setAttribute("CART_SESSION", cartProducts);
            return postCartDto;
        }

        CartProducts cart = new CartProducts();
        cart.setCartId(session.getId());
        cart.setProductId(postCartDto.getProductId());
        cart.setQuantity(postCartDto.getQuantity());

        cartProducts.add(cart);
        session.setAttribute("CART_SESSION", cartProducts);
        return postCartDto;
    }

    @Override
    public GetCartDto getAllCartProducts(HttpSession httpSession)
    {
        List<CartProducts> cartProducts = getCartProductsFromSession(httpSession);
        Cart cart = new Cart(httpSession.getId(), null);
        return myMapper.cartToCartDto(cart, cartProducts);
    }

    @Override
    public void deleteCartProductsFromSession(HttpServletRequest request)
    { request.getSession().invalidate(); }

    @Override
    public Long updateQuantiy(PostCartDto postCartDto, HttpSession httpSession)
    {
        List<CartProducts> cartProducts = getCartProductsFromSession(httpSession);
        Optional<CartProducts> firstProduct = cartProducts
                .stream()
                .filter(cartProduct -> cartProduct.getProductId()
                        .equals(postCartDto.getProductId()))
                .findFirst();

        if (firstProduct.isPresent())
        {
            firstProduct.get().setQuantity(firstProduct.get().getQuantity()+ postCartDto.getQuantity());
            httpSession.setAttribute("CART_SESSION", cartProducts);
            return postCartDto.getQuantity();
        }

        return firstProduct.get().getQuantity();
    }

    private List<CartProducts> getCartProductsFromSession(HttpSession httpSession)
    {
        List<CartProducts> cartProducts = (List<CartProducts>) httpSession.getAttribute("CART_SESSION");

        if (cartProducts == null)
            cartProducts = new ArrayList<>();

        return cartProducts;
    }
}
