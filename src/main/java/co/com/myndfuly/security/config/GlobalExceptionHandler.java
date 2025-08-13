package co.com.myndfuly.security.config;

import co.com.myndfuly.exception.RestException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(RestException.class)
  public ResponseEntity<Object> handleRestException(RestException ex) {
    Map<String, Object> body = new HashMap<>();
    body.put("status", ex.getReason().getHttpStatus().value());
    body.put("error", ex.getReason().name());
    body.put("message", ex.getMessage());

    return new ResponseEntity<>(body, ex.getReason().getHttpStatus());
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<Object> handleGenericException(Exception ex) {
    Map<String, Object> body = new HashMap<>();
    body.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
    body.put("error", "Internal Server Error");
    body.put("message", "An unexpected error occurred: " + ex.getMessage());

    return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
