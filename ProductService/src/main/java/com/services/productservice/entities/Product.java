package com.services.productservice.entities;

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
@Entity
@Table(name = "products")
public class Product
{
    @Id
    private String id;
    private String name;
    private String description;
    private BigDecimal price;
    private long quantity;
}
