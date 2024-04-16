package com.codecool.askmateoop.dao;

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

    private int questionId;
    private String question;
    private String description;
    private LocalDateTime time;

    @Value("${askmate.database.url}")
    private String databaseUrl;
    @Value("${askmate.database.username}")
    private String username;
    @Value("${askmate.database.password}")
    private String password;

    @Override
    public List<Question> getAllQuestions() {
        String sql = "SELECT * FROM question";
        List<Question> questions = new ArrayList<>();

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                questionId = resultSet.getInt("question_id");
                question = resultSet.getString("question");
                description = resultSet.getString("description");
                time = resultSet.getTimestamp("time").toLocalDateTime();
                questions.add(new Question(questionId, question, description, time));
            }
        } catch (SQLException e) {
            logger.logError(e.getMessage());
        }
        return questions;
    }

    @Override
    public Question getQuestionById(int id) {
        String sql = "SELECT * FROM question WHERE id = ?";
        Question searchedQuestion = null;

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                questionId = resultSet.getInt("question_id");
                question = resultSet.getString("title");
                description = resultSet.getString("description");
                time = resultSet.getTimestamp("creation_date").toLocalDateTime();
                searchedQuestion = new Question(questionId, question, description, time);
            }
        } catch (SQLException e) {
            logger.logError(e.getMessage());
        }
        return searchedQuestion;
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



