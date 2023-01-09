package com.services.cartservice.dtos;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Data
public class PostCartDto
{
    private Long id;
    private String cartId;
    private Long productId;
    private long quantity;
}
