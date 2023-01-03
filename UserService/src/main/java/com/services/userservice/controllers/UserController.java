package com.services.userservice.controllers;

import com.services.userservice.dtos.UserGetDto;
import com.services.userservice.dtos.UserPostDto;
import com.services.userservice.entities.Product;
import com.services.userservice.entities.User;
import com.services.userservice.mappers.MyMapper;
import com.services.userservice.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController
{
    private UserService userService;
    private MyMapper myMapper;
    private RestTemplate restTemplate;
    public UserController(UserService userService, MyMapper myMapper, RestTemplate restTemplate)
    {
        this.userService = userService;
        this.myMapper = myMapper;
        this.restTemplate = restTemplate;
    }

    @PostMapping
    private ResponseEntity<User> saveUser(@RequestBody UserPostDto userPostDto)
    {
        User user = myMapper.userPostDtoToUser(userPostDto);
        userService.createUser(user);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }
    @GetMapping
    private ResponseEntity<List<UserGetDto>> getAllUsers()
    {
        List<UserGetDto> allUsers = userService
                .readAllUsers()
                .stream().map(user -> myMapper.userToUserGetDto(user))
                .collect(Collectors.toList());

        return ResponseEntity.ok(allUsers);
    }
    @GetMapping("/{id}") ResponseEntity<UserGetDto> getUserById(@PathVariable long id)
    {
        User user = userService.readUserById(id);

        //        http://localhost:8082/products/users/2
        Product[] userProducts = restTemplate.getForObject
                ("http://localhost:8082/products/users/" + id,
                        Product[].class);

        UserGetDto userDto = myMapper.userToUserGetDto(user);

        userDto.setProducts(List.of(userProducts));
        return ResponseEntity.ok(userDto);
    }
    @PutMapping("/{id}/update")
    private ResponseEntity<User> updateUser(@PathVariable long id, @RequestBody UserPostDto userPostDto)
    {
        User user = myMapper.userPostDtoToUser(userPostDto);
        user = userService.updateUserById(id, user);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{id}/delete")
    private void deleteUser(@PathVariable long id)
    { userService.deleteUserById(id); }

}
