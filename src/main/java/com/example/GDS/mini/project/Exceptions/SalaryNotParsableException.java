package com.example.GDS.mini.project.Exceptions;

public class SalaryNotParsableException extends RuntimeException {
  private String message;

  public SalaryNotParsableException(String message) {
    super(message);
    this.message = message;
  }
}
