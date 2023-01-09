package com.services.productservice.entities;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Comment
{
    private Long id;
    private String body;
    private String productId;
    private Long userId;
}
