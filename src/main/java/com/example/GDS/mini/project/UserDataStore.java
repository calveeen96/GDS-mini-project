package com.example.GDS.mini.project;

import java.util.List;

public interface UserDataStore {

  public void saveUsers(List<User> users);

  public List<User> readUsers();

  public void seedData(List<User> users);

}
