package com.codecool.askmateoop.service;

import com.codecool.askmateoop.controller.dto.NewQuestionDTO;
import com.codecool.askmateoop.controller.dto.QuestionDTO;
import com.codecool.askmateoop.dao.QuestionsDAO;
import com.codecool.askmateoop.dao.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    private final QuestionsDAO questionsDAO;

    @Autowired
    public QuestionService(QuestionsDAO questionsDAO) {
        this.questionsDAO = questionsDAO;
    }

    public List<QuestionDTO> getAllQuestions() {
        List<Question> allQuestions = questionsDAO.getAllQuestions();
        List<QuestionDTO> convertedQuestions = new ArrayList<>();

        for (Question question : allQuestions) {
            convertedQuestions.add(new QuestionDTO(question.id(), question.title(),
                    question.description(), question.time(), question.user_id()));
        }

        return convertedQuestions;
    }

    public QuestionDTO getQuestionById(int id) {
        Question question = questionsDAO.getQuestionById(id);
        return new QuestionDTO(question.id(),question.title(),question.description(),question.time(), question.user_id());
    }
    public List<QuestionDTO> getQuestionsBySearchPhrase(String phrase){
        List<Question> allQuestions = questionsDAO.getQuestionsBySearchPhrase(phrase);
        List<QuestionDTO> convertedQuestions = new ArrayList<>();

        for (Question question : allQuestions) {
            convertedQuestions.add(new QuestionDTO(question.id(), question.title(),
                    question.description(), question.time(), question.user_id()));
        }

        return convertedQuestions;
    }

    public boolean deleteQuestionById(int id) {
        return questionsDAO.deleteQuestionById(id);
    }

    public int addNewQuestion(NewQuestionDTO question) {
        return questionsDAO.addNewQuestion(question);
    }
}
