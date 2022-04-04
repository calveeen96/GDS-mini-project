package com.example.GDS.mini.project;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class UserDataStoreImpl implements UserDataStore {

  Map<String, User> userDataBase = new HashMap<>();


  public void seedData(List<User> users) {
    users.forEach(user -> userDataBase.put(user.getName(), user));
  }

  @Override
  public void saveUsers(List<User> users) {
    users.forEach(user -> userDataBase.put(user.getName(), user));
  }

  @Override
  public List<User> readUsers() {
    return new ArrayList<User>(userDataBase.values());
  }

}
