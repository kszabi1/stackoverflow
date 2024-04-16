package com.codecool.askmateoop.controller.dto;

import java.time.LocalDateTime;

public record AnswerDTO(int id, int question_id, String message, LocalDateTime created) {
}
