package com.codecool.askmateoop.controller;

import com.codecool.askmateoop.controller.dto.NewUserDTO;
import com.codecool.askmateoop.controller.dto.UserDTO;
import com.codecool.askmateoop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> addNewUser(@RequestBody NewUserDTO user) {
        int userId = userService.addNewUser(user);
        if (userId <= 0) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(userId);
    }

    @DeleteMapping("/{id}")
    public boolean deleteUserById(@PathVariable int id) {
        return userService.deleteUser(id);
    }

    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable int id){
        return userService.getUserById(id);
    }

    @GetMapping("/by/{ids}")
    public List<UserDTO> getUsersByIds(@PathVariable List<Integer> ids) {
        return userService.getUsersByIds(ids);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody NewUserDTO user) {
        int userId = userService.login(user);

        if (userId <= 0) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(userId);
    }
}
