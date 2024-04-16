package com.codecool.askmateoop.controller.dto;

import java.time.LocalDateTime;

public record UserDTO(int id, String username, String password, LocalDateTime registrationTime) {
}
