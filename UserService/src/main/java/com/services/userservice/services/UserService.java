package com.services.userservice.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.services.userservice.dtos.UserPostDto;
import com.services.userservice.entities.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface UserService
{
    User createUser(User user, MultipartFile uploadFile) throws IOException;
    List<User> readAllUsers();
    User readUserById(long id);
    User updateUserById(long id, User user);
    void deleteUserById(long id) throws IOException;
}

