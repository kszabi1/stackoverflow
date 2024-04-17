package com.codecool.askmateoop.dao;

import com.codecool.askmateoop.controller.dto.NewAnswerDTO;
import com.codecool.askmateoop.dao.model.Answer;

import java.util.List;

public interface AnswerDAO {
    int createAnswer(NewAnswerDTO answer);
    boolean deleteAnswerById(int id);
    List<Answer> getAllAnswers();
    Answer getAnswerById(int id);
    List<Answer> getAllAnswersByQuestionId(int questionId);
}
