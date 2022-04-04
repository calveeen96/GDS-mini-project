package com.example.GDS.mini.project.Exceptions;

public class IllegalOrderParamException extends RuntimeException {
  private String message;

  public IllegalOrderParamException(String message) {
    super(message);
    this.message = message;
  }
}
