package com.services.userinterface.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@ToString
public class ProductDto
{
    private String id;
    private String name;
    private String description;
    private BigDecimal price;
    private long quantity;
    private long userId;
    private List<Comment> comments;
}
