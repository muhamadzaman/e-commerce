package com.services.userservice.implementations;

import com.services.userservice.entities.User;
import com.services.userservice.exceptions.ResourceNotFoundException;
import com.services.userservice.repositories.UserRepository;
import com.services.userservice.services.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImplementation implements UserService
{
    private UserRepository userRepository;
    public UserServiceImplementation(UserRepository userRepository)
    { this.userRepository = userRepository; }

    @Override
    public User createUser(User user) { return userRepository.save(user); }
    @Override
    public List<User> readAllUsers() { return userRepository.findAll(); }
    @Override
    public User readUserById(long id) { return findUser(id); }
    @Override
    public User updateUserById(long id, User user)
    {
        User u = findUser(id);
        user.setId(u.getId());
        return userRepository.save(user);
    }
    @Override
    public void deleteUserById(long id)
    {
        User user = findUser(id);
        userRepository.deleteById(user.getId());
    }
    private User findUser(long id)
    {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException
                        ("User with given id is not found on the server. Id found = " + id));

        return user;
    }
}