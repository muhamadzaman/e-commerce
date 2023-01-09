package com.services.cartservice.repositories;

import com.services.cartservice.entitites.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, String>
{ Cart findByUserId(Long userId); }
