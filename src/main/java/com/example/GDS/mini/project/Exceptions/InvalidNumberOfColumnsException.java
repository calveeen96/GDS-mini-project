package com.example.GDS.mini.project.Exceptions;

public class InvalidNumberOfColumnsException extends RuntimeException {

  private String message;

  public InvalidNumberOfColumnsException(String message) {
    super(message);
    this.message = message;
  }
}
