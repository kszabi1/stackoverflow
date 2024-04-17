package com.codecool.askmateoop.controller;

import com.codecool.askmateoop.controller.dto.NewUserDTO;
import com.codecool.askmateoop.controller.dto.UserDTO;
import com.codecool.askmateoop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/")
    public int addNewUser(@RequestBody NewUserDTO user) {
        return userService.addNewUser(user);
    }

    @DeleteMapping("/{id}")
    public boolean deleteUserById(@PathVariable int id) {
        return userService.deleteUser(id);
    }
    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable int id){
        return userService.getUserById(id);
    }

    @PostMapping("/login")
    public boolean login(@RequestBody NewUserDTO user) {return userService.login(user);}
}
