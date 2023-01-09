package com.services.cartservice.entitites;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "Cart")
public class Cart
{
    @Id
    private Long id;
    private Long userId;
}
