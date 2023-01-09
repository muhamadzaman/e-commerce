package com.services.userservice.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Product
{
    private String id;
    private String name;
    private String description;
    private BigDecimal price;
    private long quantity;
}
