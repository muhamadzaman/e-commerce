package com.services.userservice.dtos;

import lombok.*;

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
}
