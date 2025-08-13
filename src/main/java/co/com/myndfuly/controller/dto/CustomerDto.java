package co.com.myndfuly.controller.dto;

import java.time.LocalDate;

public record CustomerDto(
    Integer id, String name, String lastName, LocalDate birth, String email, String username) {}
