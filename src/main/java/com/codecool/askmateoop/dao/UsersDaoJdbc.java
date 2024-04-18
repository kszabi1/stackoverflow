package com.codecool.askmateoop.dao;

import com.codecool.askmateoop.configuration.Configuration;
import com.codecool.askmateoop.controller.dto.NewUserDTO;
import com.codecool.askmateoop.dao.model.Question;
import com.codecool.askmateoop.dao.model.User;
import com.codecool.askmateoop.logger.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UsersDaoJdbc implements UsersDAO {
    private final Logger logger;
    private final Configuration configuration;

    @Autowired
    public UsersDaoJdbc(Logger logger, Configuration configuration) {
        this.logger = logger;
        this.configuration = configuration;
    }

    private String username;
    private String password;
    private LocalDateTime time;
    private int userId;

    @Override
    public List<User> getAllUsers() {
        String sql = "SELECT user_id, username, password, registration_time FROM users";

        List<User> users = new ArrayList<>();

        try (Connection connection = configuration.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                userId = resultSet.getInt("user_id");
                username = resultSet.getString("username");
                password = resultSet.getString("password");
                time = resultSet.getTimestamp("registration_time").toLocalDateTime();
                users.add(new User(userId, username, password, time));
            }
        } catch (SQLException e) {
            logger.logError(e.getMessage());
        }
        return users;
    }

    @Override
    public int addNewUser(NewUserDTO user) {
        String sql = "INSERT INTO users(username, password) VALUES(?, ?)";
        Question newUser = null;

        try (Connection connection = configuration.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.username());
            preparedStatement.setString(2, user.password());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int userId = generatedKeys.getInt(1);
                logger.logInfo("New User created with id: " + userId);
                return userId;
            } else {
                throw new SQLException("Creating User failed, no ID obtained.");
            }
        } catch (SQLException e) {
            logger.logError(e.getMessage());
        }
        return 0;
    }

    @Override
    public User getUserById(int id) {
        String sql = "SELECT user_id, username, password, registration_time FROM users WHERE user_id = ?";
        User searchedUser = null;

        try (Connection connection = configuration.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                userId = resultSet.getInt("user_id");
                username = resultSet.getString("username");
                password = resultSet.getString("password");
                time = resultSet.getTimestamp("registration_time").toLocalDateTime();
                searchedUser = new User(userId, username, password, time);
            }
        } catch (SQLException e) {
            logger.logError(e.getMessage());
        }
        return searchedUser;
    }

    @Override
    public boolean deleteUserById(int id) {
        String sql = "DELETE FROM users WHERE user_id = ?";

        try (Connection connection = configuration.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int userId = generatedKeys.getInt(1);
                logger.logInfo("User deleted with id: " + userId);
                return true;
            } else {
                throw new SQLException("Deleting question failed, no ID obtained.");
            }
        } catch (SQLException e) {
            logger.logError(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean login(NewUserDTO user) {
        boolean result = false;
        try (Connection connection = configuration.getConnection()) {
            String sql = "SELECT username, password FROM users WHERE username = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, user.username());
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String password = resultSet.getString("password");
                result = user.password().equals(password);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            logger.logError(e.getMessage());
        }

        return result;
    }
}
