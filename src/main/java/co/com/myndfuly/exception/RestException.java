package co.com.myndfuly.exception;

import co.com.myndfuly.exception.enums.ErrorReason;

public class RestException extends RuntimeException {

  private final ErrorReason reason;
  private final String message;

  RestException(ErrorReason reason, String message) {
    this.reason = reason;
    this.message = message;
  }

  public static RestExceptionBuilder builder() {
    return new RestExceptionBuilder();
  }

  public ErrorReason getReason() {
    return this.reason;
  }

  public String getMessage() {
    return this.message;
  }

  public static class RestExceptionBuilder {
    private ErrorReason reason;
    private String message;

    RestExceptionBuilder() {}

    public RestExceptionBuilder reason(ErrorReason reason) {
      this.reason = reason;
      return this;
    }

    public RestExceptionBuilder message(String message) {
      this.message = message;
      return this;
    }

    public RestException build() {
      return new RestException(this.reason, this.message);
    }
  }
}
