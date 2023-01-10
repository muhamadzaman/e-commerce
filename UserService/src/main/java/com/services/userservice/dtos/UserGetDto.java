package com.services.userservice.dtos;

import com.services.userservice.entities.Product;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Data
public class UserGetDto
{
    private long id;
    private String username;
    private String email;
    private String phoneNumber;
    private List<Product> products;

    private String imageUrl;
    private String imageId;
}
