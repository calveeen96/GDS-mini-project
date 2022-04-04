package com.example.GDS.mini.project;

import com.example.GDS.mini.project.Exceptions.IllegalOrderParamException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserServiceTest {

  private UserService userService;

  private UserDataStore userDataStore;

  @BeforeEach
  public void setup() {
    userService = new UserService();

    userDataStore = mock(UserDataStoreImpl.class);

    userService.setUserDataStore(userDataStore);
  }

  @Test
  void getUsers_IllegalOrderParameter_ThrowsException() {
    List<User> users = new ArrayList<>();
    User testUser1 = new User("John Doe", 1000);
    User testUser2 = new User("Mary Jane", 6000);
    User testUser3 = new User("Jake Paul", 2500);

    users.add(testUser1);
    users.add(testUser2);
    users.add(testUser3);

    when(userDataStore.readUsers()).thenReturn(users);

    assertThatThrownBy(() -> userService.getUsers(0, 5000, 0, Optional.empty(), Optional.of("world")))
        .isInstanceOf(IllegalOrderParamException.class)
        .hasMessageContaining("Can only sort by Name or Salary. Cannot sort by world");
  }


  @Test
  void getUsers_MinAndMaxSalary_Success() {
    List<User> users = new ArrayList<>();
    User testUser1 = new User("John Doe", 1000);
    User testUser2 = new User("Mary Jane", 6000);
    User testUser3 = new User("Jake Paul", 2500);

    users.add(testUser1);
    users.add(testUser2);
    users.add(testUser3);

    when(userDataStore.readUsers()).thenReturn(users);

    List<User> result = userService.getUsers(0, 5000, 0, Optional.empty(), Optional.empty());

    assertThat(result.size()).isEqualTo(2);
    assertThat(result.get(0)).isEqualTo(testUser1);
  }

  @Test
  void getUsers_MinAndMaxSalarySortByName_Success() {
    List<User> users = new ArrayList<>();
    User testUser3 = new User("Cassandra Doe", 3000);
    User testUser2 = new User("Boaz Doe", 2000);
    User testUser1 = new User("Abram Doe", 1000);
    User testUser5 = new User("Elijah Doe", 2100);
    User testUser4 = new User("Denise Doe", 4000);
    User testUser6 = new User("Abel Doe", 12000);

    users.add(testUser1);
    users.add(testUser2);
    users.add(testUser3);
    users.add(testUser4);
    users.add(testUser5);
    users.add(testUser6);

    when(userDataStore.readUsers()).thenReturn(users);

    List<User> result = userService.getUsers(0, 5000, 0, Optional.empty(), Optional.of("Name"));

    assertThat(result.size()).isEqualTo(5);
    assertThat(result.get(0)).isEqualTo(testUser1);
    assertThat(result.get(1)).isEqualTo(testUser2);
    assertThat(result.get(2)).isEqualTo(testUser3);
    assertThat(result.get(3)).isEqualTo(testUser4);
    assertThat(result.get(4)).isEqualTo(testUser5);
  }


}