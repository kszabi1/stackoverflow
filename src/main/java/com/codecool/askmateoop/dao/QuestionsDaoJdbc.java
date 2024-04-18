package com.codecool.askmateoop.dao;

import com.codecool.askmateoop.configuration.Configuration;
import com.codecool.askmateoop.controller.dto.NewQuestionDTO;
import com.codecool.askmateoop.dao.model.Question;
import com.codecool.askmateoop.logger.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class QuestionsDaoJdbc implements QuestionsDAO {

    private final Logger logger;
    private final Configuration conf;

    @Autowired
    public QuestionsDaoJdbc(Logger logger, Configuration conf) {
        this.logger = logger;
        this.conf = conf;
    }

    private int questionId;
    private String question;
    private String description;
    private LocalDateTime time;


    @Override
    public List<Question> getAllQuestions() {
        String sql = "SELECT * FROM questions";

        List<Question> questions = new ArrayList<>();


        try (Connection connection = conf.getConnection();
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

        try (Connection connection = conf.getConnection();
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

    @Override
    public Question getQuestionById(int id) {
        String sql = "SELECT * FROM questions WHERE question_id = ?";
        Question searchedQuestion = null;

        try (Connection connection = conf.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                questionId = resultSet.getInt("question_id");
                question = resultSet.getString("question");
                description = resultSet.getString("description");
                time = resultSet.getTimestamp("creation_time").toLocalDateTime();
                searchedQuestion = new Question(questionId, question, description, time);
            }
        } catch (SQLException e) {
            logger.logError(e.getMessage());
        }
        return searchedQuestion;
    }

    @Override
    public boolean deleteQuestionById(int id) {
        String sql = "DELETE FROM questions WHERE question_id = ?";

        try (Connection connection = conf.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int questionId = generatedKeys.getInt(1);
                logger.logInfo("Question deleted with id: " + questionId);
                return true;
            } else {
                throw new SQLException("Deleting question failed, no ID obtained.");
            }
        } catch (SQLException e) {
            logger.logError(e.getMessage());
        }
        return false;
    }

}



