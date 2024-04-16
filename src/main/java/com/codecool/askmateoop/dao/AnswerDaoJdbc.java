package com.codecool.askmateoop.dao;

import com.codecool.askmateoop.configuration.Configuration;
import com.codecool.askmateoop.controller.dto.NewAnswerDTO;
import com.codecool.askmateoop.dao.model.Answer;
import com.codecool.askmateoop.dao.model.Question;
import com.codecool.askmateoop.logger.ConsoleLogger;
import com.codecool.askmateoop.logger.Logger;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AnswerDaoJdbc implements AnswerDAO{
    private final Logger logger = new ConsoleLogger();
    private final Configuration conf = new Configuration();

    private int answerId;
    private int question_id;
    private String message;
    private LocalDateTime time;

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

    @Override
    public boolean deleteAnswerById(int id) {
        String sql = "DELETE FROM answers WHERE answer_id = ?";

        try (Connection connection = conf.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int answer_id = generatedKeys.getInt(1);
                logger.logInfo("Answer deleted with id: " + answer_id);
                return true;
            } else {
                throw new SQLException("Deleting answer failed, no ID obtained.");
            }
        } catch (SQLException e) {
            logger.logError(e.getMessage());
        }
        return false;
    }

    @Override
    public List<Answer> getAllAnswers() {
        String sql = "SELECT * FROM answers";

        List<Answer> answers = new ArrayList<>();

        try (Connection connection = conf.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                answerId = resultSet.getInt("answer_id");
                question_id = resultSet.getInt("question_id");
                message = resultSet.getString("message");
                time = resultSet.getTimestamp("creation_date").toLocalDateTime();
                answers.add(new Answer(answerId, question_id, message, time));
            }
        } catch (SQLException e) {
            logger.logError(e.getMessage());
        }
        return answers;
    }

    @Override
    public Answer getAnswerById(int id) {
        String sql = "SELECT * FROM answers WHERE answer_id = ?";
        Answer searchedAnswer = null;

        try (Connection connection = conf.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                answerId = resultSet.getInt("answer_id");
                question_id = resultSet.getInt("question_id");
                message = resultSet.getString("message");
                time = resultSet.getTimestamp("creation_date").toLocalDateTime();
                searchedAnswer = new Answer(answerId, question_id, message, time);
            }
        } catch (SQLException e) {
            logger.logError(e.getMessage());
        }
        return searchedAnswer;
    }
}
