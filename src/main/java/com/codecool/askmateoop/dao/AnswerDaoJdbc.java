package com.codecool.askmateoop.dao;

import com.codecool.askmateoop.configuration.Configuration;
import com.codecool.askmateoop.controller.dto.NewAnswerDTO;
import com.codecool.askmateoop.dao.model.Answer;
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

    @Override
    public int createAnswer(NewAnswerDTO answer) {
        String sql = "INSERT INTO answers(question_id, message, user_id) VALUES(?, ?, ?)";

        try (Connection connection = conf.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, answer.question_id());
            preparedStatement.setString(2, answer.message());
            preparedStatement.setInt(3, answer.user_id());
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

        try (Connection connection = conf.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            if (preparedStatement.executeUpdate() > 0) {
                logger.logInfo("Answer deleted with id: " + id);
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
        String sql = "SELECT answer_id, question_id, message, registration_date, user_id FROM answers";

        List<Answer> answers = new ArrayList<>();

        try (Connection connection = conf.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            answers = getAnswers(resultSet);
        } catch (SQLException e) {
            logger.logError(e.getMessage());
        }
        return answers;
    }

    @Override
    public Answer getAnswerById(int id) {
        String sql = "SELECT answer_id, question_id, message, registration_date, user_id FROM answers WHERE answer_id = ?";
        Answer searchedAnswer = null;

        try (Connection connection = conf.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int answerId = resultSet.getInt("answer_id");
                int question_id = resultSet.getInt("question_id");
                String message = resultSet.getString("message");
                LocalDateTime time = resultSet.getTimestamp("creation_date").toLocalDateTime();
                int userId = resultSet.getInt("user_id");
                searchedAnswer = new Answer(answerId, question_id, message, time, userId);
            }
        } catch (SQLException e) {
            logger.logError(e.getMessage());
        }
        return searchedAnswer;
    }

    @Override
    public  List<Answer> getAllAnswersByQuestionId(int questionId) {
        String sql = "SELECT answer_id, question_id, message, registration_date, user_id FROM answers WHERE question_id = ?";
        List<Answer> answers = new ArrayList<>();

        try (Connection connection = conf.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, questionId);
            ResultSet resultSet = statement.executeQuery();
            answers = getAnswers(resultSet);

        } catch (SQLException e) {
            logger.logError(e.getMessage());
        }
        return answers;
    }

    private List<Answer> getAnswers(ResultSet resultSet) throws SQLException {
        List<Answer> answers = new ArrayList<>();

        while (resultSet.next()) {
            int answerId = resultSet.getInt("answer_id");
            int question_id = resultSet.getInt("question_id");
            String message = resultSet.getString("message");
            LocalDateTime time = resultSet.getTimestamp("registration_date").toLocalDateTime();
            int userId = resultSet.getInt("user_id");
            answers.add(new Answer(answerId, question_id, message, time, userId));
        }
        return answers;
    }
}
