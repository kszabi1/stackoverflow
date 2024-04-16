package com.codecool.askmateoop.service;

import com.codecool.askmateoop.controller.dto.NewAnswerDTO;
import com.codecool.askmateoop.dao.AnswerDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


}
