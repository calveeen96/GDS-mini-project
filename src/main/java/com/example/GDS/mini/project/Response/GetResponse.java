package com.example.GDS.mini.project.Response;

import com.example.GDS.mini.project.User;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

public class GetResponse {

  @JsonInclude(JsonInclude.Include.NON_NULL)
  List<User> results;


  @JsonInclude(JsonInclude.Include.NON_NULL)
  String errorMessage;

  public GetResponse(List<User> results) {
    this.results = results;
  }

  public GetResponse(String errorMessage) {
    this.errorMessage = errorMessage;
  }

  public List<User> getResults() {
    return results;
  }

  public void setResults(List<User> results) {
    this.results = results;
  }

  public String getErrorMessage() {
    return errorMessage;
  }

  public void setErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
  }

}
