package com.services.userservice.services;

import com.services.userservice.entities.User;

import java.util.List;

public interface UserService
{
    User createUser(User user);
    List<User> readAllUsers();
    User readUserById(long id);
    User updateUserById(long id, User user);
    void deleteUserById(long id);
}

