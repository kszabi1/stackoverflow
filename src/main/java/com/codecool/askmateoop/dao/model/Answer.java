package com.codecool.askmateoop.dao.model;

import java.time.LocalDateTime;

public record Answer(int id, int question_id, String message, LocalDateTime time) {
}
