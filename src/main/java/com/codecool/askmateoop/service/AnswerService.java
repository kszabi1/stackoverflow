package com.codecool.askmateoop.service;

import com.codecool.askmateoop.controller.dto.NewAnswerDTO;
import com.codecool.askmateoop.dao.AnswerDAO;
import com.codecool.askmateoop.dao.model.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerService {
    private final AnswerDAO answerDAO;

    @Autowired
    public AnswerService(AnswerDAO answerDAO) {
        this.answerDAO = answerDAO;
    }

    public int createAnswer(NewAnswerDTO answer) {
        return answerDAO.createAnswer(answer);
    }

    public boolean deleteAnswerById(int id) {return answerDAO.deleteAnswerById(id); }

    public List<Answer> getAllAnswer() {return answerDAO.getAllAnswers(); }

    public Answer getAnswerById(int id) {return answerDAO.getAnswerById(id); }

    public List<Answer> getAllAnswerByQuestionId(int question_id) {return answerDAO.getAllAnswersByQuestionId(question_id);}
}
