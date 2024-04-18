package com.codecool.askmateoop.service;

import com.codecool.askmateoop.controller.dto.NewUserDTO;
import com.codecool.askmateoop.controller.dto.UserDTO;
import com.codecool.askmateoop.dao.UsersDAO;
import com.codecool.askmateoop.dao.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private UsersDAO usersDAO;

    @Autowired
    public UserService(UsersDAO usersDAO) {
        this.usersDAO = usersDAO;
    }

    public List<UserDTO> getAllUsers() {
        List<User> users = usersDAO.getAllUsers();
        List<UserDTO> convertedUsers = new ArrayList<>();

        for (User user : users) {
            convertedUsers.add(new UserDTO(user.id(), user.username(), user.registrationTime()));
        }
        return convertedUsers;
    }

    public int addNewUser(NewUserDTO user) {
        return usersDAO.addNewUser(user);
    }

    public boolean deleteUser(int id) {
        return usersDAO.deleteUserById(id);
    }

    public UserDTO getUserById(int id) {
        User user = usersDAO.getUserById(id);
        return new UserDTO(user.id(), user.username(), user.registrationTime());
    }

    public boolean login(NewUserDTO user) {return usersDAO.login(user);}

}
