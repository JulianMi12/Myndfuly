package co.com.myndfuly.service;

import co.com.myndfuly.data.SessionResponseData;
import co.com.myndfuly.data.SignUpRequestData;

public interface AuthService {
  SessionResponseData signUp(SignUpRequestData signUpRequest);

  SessionResponseData login(String email, String password);
}
