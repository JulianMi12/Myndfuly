package co.com.myndfuly.exception.enums;

import org.springframework.http.HttpStatus;

public enum ErrorReason {
  BAD_REQUEST("000400", HttpStatus.BAD_REQUEST),
  NOT_FOUND("000404", HttpStatus.NOT_FOUND),
  INTERNAL_SERVER_ERROR("000500", HttpStatus.INTERNAL_SERVER_ERROR),
  FORBIDDEN("000403", HttpStatus.FORBIDDEN),
  UNAUTHORIZED("000401", HttpStatus.UNAUTHORIZED),
  ;

  private final HttpStatus httpStatus;

  private ErrorReason(String errorCode, HttpStatus httpStatus) {
    this.httpStatus = httpStatus;
  }

  public HttpStatus getHttpStatus() {
    return this.httpStatus;
  }
}
