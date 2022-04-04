package com.example.GDS.mini.project.Response;

import com.fasterxml.jackson.annotation.JsonInclude;

public class UploadFileResponse {

  private int success;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String errorMessage;

  public UploadFileResponse(int success) {
    this.success = success;
  }

  public UploadFileResponse(int success, String errorMessage) {
    this.success = success;
    this.errorMessage = errorMessage;
  }

  public int getSuccess() {
    return success;
  }

  public void setSuccess(int success) {
    this.success = success;
  }

  public String getErrorMessage() {
    return errorMessage;
  }

  public void setErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
  }
}
