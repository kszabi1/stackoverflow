package com.codecool.askmateoop.dao;

import com.codecool.askmateoop.controller.dto.NewAnswerDTO;

public interface AnswerDAO {
    int createAnswer(NewAnswerDTO answer);
}
