package com.example.GDS.mini.project;

public class User {

  String name;
  float salary;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public float getSalary() {
    return salary;
  }

  public void setSalary(float salary) {
    this.salary = salary;
  }

  public User(String name, float salary) {
    this.name = name;
    this.salary = salary;
  }
}
