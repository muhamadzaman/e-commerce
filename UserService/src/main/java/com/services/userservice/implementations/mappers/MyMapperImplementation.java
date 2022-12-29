package com.services.userservice.implementations.mappers;

import com.services.userservice.dtos.UserGetDto;
import com.services.userservice.dtos.UserPostDto;
import com.services.userservice.entities.User;
import com.services.userservice.mappers.MyMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class MyMapperImplementation implements MyMapper
{
    private ModelMapper modelMapper;
    public MyMapperImplementation(ModelMapper modelMapper)
    { this.modelMapper = modelMapper; }

    @Override
    public UserGetDto userToUserGetDto(User user)
    {
        UserGetDto userGetDto = new UserGetDto();

        userGetDto.setId(user.getId());
        userGetDto.setUsername(user.getUsername());
        userGetDto.setEmail(user.getEmail());
        userGetDto.setPhoneNumber(user.getPhoneNumber());

        return userGetDto;
    }

    @Override
    public User userPostDtoToUser(UserPostDto userPostDto)
    {
        User user = modelMapper.map(userPostDto, User.class);
        return user;
    }
}

