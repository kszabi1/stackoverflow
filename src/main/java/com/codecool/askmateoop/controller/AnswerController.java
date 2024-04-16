package com.codecool.askmateoop.controller;

import com.codecool.askmateoop.controller.dto.NewAnswerDTO;
import com.codecool.askmateoop.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/answer")
public class AnswerController {
    private final AnswerService answerService;

    @Autowired
    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    @PostMapping("/{question_id}")
    public int createAnswer(@RequestBody NewAnswerDTO answer) {
        return answerService.createAnswer(answer);
    }
}
