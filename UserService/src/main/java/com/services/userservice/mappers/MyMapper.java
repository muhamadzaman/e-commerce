package com.services.userservice.mappers;

import com.services.userservice.dtos.UserGetDto;
import com.services.userservice.dtos.UserPostDto;
import com.services.userservice.entities.User;

public interface MyMapper
{
    UserGetDto userToUserGetDto(User user);
    User userPostDtoToUser(UserPostDto userPostDto);
}
