package co.com.myndfuly.controller.impl;

import co.com.myndfuly.controller.AuthController;
import co.com.myndfuly.controller.dto.LoginRequestDto;
import co.com.myndfuly.controller.dto.SessionResponseDto;
import co.com.myndfuly.controller.dto.SignUpRequestDto;
import co.com.myndfuly.mapper.AuthMapper;
import co.com.myndfuly.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping("/myndfuly/auth")
public class AuthControllerImpl implements AuthController {

  private final AuthService authService;

  @Override
  @PostMapping("/sign-up")
  public SessionResponseDto signUp(@RequestBody SignUpRequestDto signUpRequestDto) {
    return AuthMapper.INSTANCE.toDto(
        authService.signUp(AuthMapper.INSTANCE.toData(signUpRequestDto)));
  }

  @Override
  @PostMapping("/login")
  public SessionResponseDto logIn(@RequestBody LoginRequestDto loginRequestDto) {
    return AuthMapper.INSTANCE.toDto(
        authService.login(loginRequestDto.email(), loginRequestDto.password()));
  }
}
