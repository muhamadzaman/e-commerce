package com.services.productservice.dtos;

import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Data
public class ProductDto
{
    private String id;
    private String name;
    private String description;
    private BigDecimal price;
    private long quantity;
}
