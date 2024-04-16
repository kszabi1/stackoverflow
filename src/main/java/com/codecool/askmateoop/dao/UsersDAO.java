package com.codecool.askmateoop.dao;

import com.codecool.askmateoop.controller.dto.NewUserDTO;
import com.codecool.askmateoop.dao.model.User;

import java.util.List;

public interface UsersDAO {
    List<User> getAllUsers();
    int addNewUser(NewUserDTO user);
    User getUserById(int id);
    boolean deleteUserById(int id);
}
