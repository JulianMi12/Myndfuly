package co.com.myndfuly.controller.dto;

import java.time.LocalDate;

public record ContactDto(
    Integer id,
    String name,
    LocalDate birth,
    Integer age,
    String callingCode,
    String phoneNumber) {}
