package com.codecool.askmateoop.configuration;

import com.codecool.askmateoop.dao.QuestionsDAO;
import com.codecool.askmateoop.dao.QuestionsDaoJdbc;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;

@SpringBootConfiguration
public class Configuration {

    @Value("${askmate.database.url}")
    private String databaseUrl;

    public QuestionsDAO questionsDAO() {
        return new QuestionsDaoJdbc();
    }

}
