package com.services.productservice.dtos;

import com.services.productservice.entities.Comment;
import lombok.*;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Data
public class ProductDto
{
    private String id;
    @NotNull(message = "Name of the product must not be null.")
    @NotBlank(message = "Name of product can't be blank.")
    @Size(min = 2, max = 15, message = "Name of product should be between 2 and 15 characters.")
    private String name;
    @NotNull(message = "Description of the product must not be null.")
    @NotBlank(message = "Description of product can't be blank.")
    @Size(min = 10, max = 50, message = "Name of product should be between 10 and 50 characters.")
    private String description;
    @Min(value = 1, message = "Price of product should be atleast 1")
    private BigDecimal price;
    @Min(value = 1, message = "Quantity of product should be atleast 1")
    private long quantity;
    @Min(value = 1, message = "User id must be provided")
    private long userId;
    private List<Comment> comments;
}
