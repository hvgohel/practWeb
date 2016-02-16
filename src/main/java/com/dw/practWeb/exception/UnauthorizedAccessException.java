package com.dw.practWeb.exception;

import com.dw.practWeb.dto.ErrorInfo;

public class UnauthorizedAccessException extends RuntimeException implements ErrorInfo {
  private String code;

  public UnauthorizedAccessException() {}

  public UnauthorizedAccessException(String msg) {
    super(msg);
  }

  public UnauthorizedAccessException(String code, String msg) {
    super(msg);
    this.code = code;
  }

  @Override
  public String getCode() {
    return code;
  }

}
