package com.services.cartservice.repositories;

import com.services.cartservice.entitites.CartProducts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartProductsRepository extends JpaRepository<CartProducts, Long>
{ List<CartProducts> findByCartId(String cartId); }
