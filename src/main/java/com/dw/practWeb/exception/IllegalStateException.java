package com.dw.practWeb.exception;

import com.dw.practWeb.dto.ErrorInfo;

public class IllegalStateException extends RuntimeException implements ErrorInfo {
  private String code;

  public IllegalStateException() {}

  public IllegalStateException(String msg) {
    super(msg);
  }

  public IllegalStateException(String code, String msg) {
    super(msg);
    this.code = code;
  }

  @Override
  public String getCode() {
    return code;
  }

}
