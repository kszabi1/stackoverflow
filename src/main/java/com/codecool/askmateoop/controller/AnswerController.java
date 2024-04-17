package com.codecool.askmateoop.controller;

import com.codecool.askmateoop.controller.dto.NewAnswerDTO;
import com.codecool.askmateoop.dao.model.Answer;
import com.codecool.askmateoop.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @DeleteMapping("/{id}")
    public boolean deleteAnswerById(@PathVariable int id) { return answerService.deleteAnswerById(id); }

    @GetMapping("/all")
    public List<Answer> getAllAnswer() {return answerService.getAllAnswer();}

    @GetMapping("/{id}")
    public Answer getAnswerById(@PathVariable int id) { return answerService.getAnswerById(id); }

    @GetMapping("/all/{question_id}")
    public List<Answer> getAllAnswerByQuestionId(@PathVariable int question_id) {return answerService.getAllAnswerByQuestionId(question_id);}

}
