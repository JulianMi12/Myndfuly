package co.com.myndfuly.controller;

import co.com.myndfuly.controller.dto.LoginRequestDto;
import co.com.myndfuly.controller.dto.SessionResponseDto;
import co.com.myndfuly.controller.dto.SignUpRequestDto;

public interface AuthController {
  SessionResponseDto signUp(SignUpRequestDto signUpRequestDto);

  SessionResponseDto logIn(LoginRequestDto loginRequestDto);
}
