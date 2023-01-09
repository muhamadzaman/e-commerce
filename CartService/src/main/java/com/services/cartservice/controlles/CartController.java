package com.services.cartservice.controlles;

import com.services.cartservice.dtos.GetCartDto;
import com.services.cartservice.dtos.PostCartDto;
import com.services.cartservice.services.CartService;
import com.services.cartservice.services.SessionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/carts")
public class CartController
{
    private CartService cartService;
    private SessionService sessionService;
    public CartController(CartService cartService, SessionService sessionService)
    {
        this.cartService = cartService;
        this.sessionService = sessionService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Object> findCartByUserId(@PathVariable Long userId)
    {
        GetCartDto cart = cartService.getCartByUserId(userId);
        return ResponseEntity.ok(cart);
    }

    @PostMapping("/save-session")
    public ResponseEntity<PostCartDto> saveCartToSession(HttpServletRequest request, @RequestBody PostCartDto postCartDTO)
    {
        PostCartDto postCart = sessionService.saveCartProductsToSession(request, postCartDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(postCart);
    }

    @PostMapping("/{userId}")
    public ResponseEntity<GetCartDto> saveSessionToDatabase(HttpSession session, @PathVariable Long userId)
    {
        GetCartDto getCartDto = cartService.saveSessionFromCartProductsToDatabase(session,userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(getCartDto);
    }

    @GetMapping("/get-session")
    public ResponseEntity<GetCartDto> getCartItemsToSession(HttpSession httpSession)
    {
        GetCartDto allCart = sessionService.getAllCartProducts(httpSession);
        return ResponseEntity.ok(allCart);
    }

    @PostMapping("/session/update-quantiy")
    public ResponseEntity<Long> addQuantity(@RequestBody PostCartDto postCartDTO, HttpSession httpSession)
    {
        Long updatedQuantity = sessionService.updateQuantiy(postCartDTO, httpSession);
        return ResponseEntity.ok(updatedQuantity);
    }

    @DeleteMapping("/session/delete")
    public ResponseEntity<Void> deleteCartToSession(HttpServletRequest request)
    {
        sessionService.deleteCartProductsFromSession(request);
        return ResponseEntity.noContent().build();

    }
}
