package com.services.userservice.dtos;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Data
public class UserPostDto
{
    private long id;
    private String username;
    private String password;
    private String email;
    private String phoneNumber;
}
