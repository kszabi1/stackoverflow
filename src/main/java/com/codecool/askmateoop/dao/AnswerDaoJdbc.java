package com.codecool.askmateoop.dao;

import com.codecool.askmateoop.configuration.Configuration;
import com.codecool.askmateoop.controller.dto.NewAnswerDTO;
import com.codecool.askmateoop.logger.ConsoleLogger;
import com.codecool.askmateoop.logger.Logger;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class AnswerDaoJdbc implements AnswerDAO{
    private final Logger logger = new ConsoleLogger();
    private final Configuration conf = new Configuration();

    @Override
    public int createAnswer(NewAnswerDTO answer) {
        String sql = "INSERT INTO answers(question_id, message) VALUES(?, ?)";

        try (Connection connection = conf.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, answer.question_id());
            preparedStatement.setString(2, answer.message());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int answerId = generatedKeys.getInt(1);
                logger.logInfo("New answer created with id: " + answerId);
                return answerId;
            } else {
                throw new SQLException("Creating answer failed, no ID obtained.");
            }
        } catch (SQLException e) {
            logger.logError(e.getMessage());
        }
        return 0;
    }
}
