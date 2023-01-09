package com.services.cartservice.dtos;

import com.services.cartservice.entitites.CartProducts;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Data
public class GetCartDto
{
    private String id;
    private Long userId;
    private List<CartProducts> cartProducts;
}
