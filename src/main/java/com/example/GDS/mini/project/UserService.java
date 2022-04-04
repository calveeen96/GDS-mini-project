package com.example.GDS.mini.project;

import com.example.GDS.mini.project.Exceptions.IllegalOrderParamException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class UserService {

  @Autowired
  UserDataStore userDataStore;

  public void setUserDataStore(UserDataStore userDataStore) {
    this.userDataStore = userDataStore;
  }

  public void updateUserDatabase(List<User> newUsers) {
    userDataStore.saveUsers(newUsers);
  }


  public List<User> getUsers(
      float minSalary,
      float maxSalary,
      int offset,
      Optional<Integer> limit,
      Optional<String> sort) throws IllegalOrderParamException
  {
    if (sort.isPresent() && !(sort.get().equals("Name") || sort.get().equals("Salary"))) {
      throw new IllegalOrderParamException("Can only sort by Name or Salary. Cannot sort by " + sort.get());
    }

    List<User> userList = userDataStore.readUsers();

    Stream<User> userStream = userList.stream()
        .filter((user) -> user.getSalary() >= minSalary)
        .filter((user) -> user.getSalary() <= maxSalary)
        .skip(offset);

    if (limit.isPresent()) {
      userStream = userStream.limit(limit.get());
    }

    if (sort.isPresent() && sort.get().equals("Name")) {
      userStream = userStream.sorted(Comparator.comparing(User::getName));
    }
    if (sort.isPresent() && sort.get().equals("Salary")) {
      userStream = userStream.sorted(Comparator.comparing(User::getSalary));
    }

    return userStream.collect(Collectors.toList());
  }
}
