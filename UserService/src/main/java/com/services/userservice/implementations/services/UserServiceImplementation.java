package com.services.userservice.implementations.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.services.cloudinaryservice.services.CloudinaryService;
import com.services.userservice.dtos.UserPostDto;
import com.services.userservice.entities.User;
import com.services.userservice.exceptions.ResourceNotFoundException;
import com.services.userservice.repositories.UserRepository;
import com.services.userservice.services.UserService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImplementation implements UserService
{
    private UserRepository userRepository;
    private CloudinaryService cloudinaryService;
    private ObjectMapper objectMapper;
    public UserServiceImplementation
            (UserRepository userRepository,
             CloudinaryService cloudinaryService,
             ObjectMapper objectMapper)
    {
        this.userRepository = userRepository;
        this.cloudinaryService  = cloudinaryService;
        this.objectMapper = objectMapper;
    }

    @Override
    public User createUser(User user, MultipartFile uploadFile) throws IOException
    {
        Map uploadResult = cloudinaryService.upload(uploadFile);
        user.setImageId(uploadResult.get("public_id").toString());
        user.setImageUrl(uploadResult.get("secure_url").toString());
        return userRepository.save(user);
    }
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
    @Override
    public UserPostDto getJson(String user) throws JsonProcessingException
    {
        UserPostDto userJson = objectMapper.readValue(user, UserPostDto.class);
        return userJson;
    }
}