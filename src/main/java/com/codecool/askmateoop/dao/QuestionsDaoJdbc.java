package com.codecool.askmateoop.dao;

import com.codecool.askmateoop.controller.dto.NewQuestionDTO;
import com.codecool.askmateoop.dao.model.Question;
import com.codecool.askmateoop.logger.ConsoleLogger;
import com.codecool.askmateoop.logger.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class QuestionsDaoJdbc implements QuestionsDAO {

    private final Logger logger = new ConsoleLogger();

    //    TODO: Add the url of your database to the Environment Variables of the Run Configuration
    @Value("${askmate.database.url}")
    private String databaseUrl;
    @Value("${askmate.database.username}")
    private String username;
    @Value("${askmate.database.password}")
    private String password;

    @Override
    public List<Question> getAllQuestions() {
        String sql = "SELECT * FROM questions";

        List<Question> questions = new ArrayList<>();
        int questionId;
        String question;
        String description;
        LocalDateTime time;

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                questionId = resultSet.getInt("question_id");
                question = resultSet.getString("question");
                description = resultSet.getString("description");
                time = resultSet.getTimestamp("creation_time").toLocalDateTime();
                questions.add(new Question(questionId, question, description, time));
            }
        } catch (SQLException e) {
            logger.logError(e.getMessage());
        }
        return questions;
    }

    @Override
    public int addNewQuestion(NewQuestionDTO question) {
        String sql = "INSERT INTO questions(question, description) VALUES(?, ?)";
        Question newQuestion = null;

        try (Connection connection = this.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, question.title());
            preparedStatement.setString(2, question.description());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int questionId = generatedKeys.getInt(1);
                logger.logInfo("New Question created with id: " + questionId);
                return questionId;
            } else {
                throw new SQLException("Creating question failed, no ID obtained.");
            }
        } catch (SQLException e) {
            logger.logError(e.getMessage());
        }
        return 0;
    }

    private Connection getConnection() {
        try {
            return DriverManager.getConnection(databaseUrl, username, password);
        } catch (SQLException ex) {
            System.err.println("Could not create database connection.");
            throw new RuntimeException(ex);
        }
    }
}



