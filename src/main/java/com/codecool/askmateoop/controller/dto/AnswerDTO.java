package com.codecool.askmateoop.controller.dto;

import java.time.LocalDateTime;

public record AnswerDTO(int id, String title, String description, LocalDateTime created) {
}
