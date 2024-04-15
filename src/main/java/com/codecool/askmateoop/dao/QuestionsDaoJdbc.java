package com.codecool.askmateoop.dao;

import com.codecool.askmateoop.dao.model.Question;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class QuestionsDaoJdbc implements QuestionsDAO {

    //    TODO: Add the url of your database to the Environment Variables of the Run Configuration
    //    @Value("${askmate.database.url}")
    //    private String databaseUrl;

    @Override
    public List<Question> getAllQuestions() {
        // TODO SQL query questions from database
        // prepared statement
        throw new UnsupportedOperationException();
    }
}
