package com.example.GDS.mini.project;

import com.example.GDS.mini.project.Exceptions.IllegalOrderParamException;
import com.example.GDS.mini.project.Response.GetResponse;
import com.example.GDS.mini.project.Response.UploadFileResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

  @Autowired
  UserCsvParser userCsvParser;

  @Autowired
  UserService userService;

  @GetMapping(value = "/users")
  public ResponseEntity<GetResponse> getUserData(
      @RequestParam(defaultValue = "0") float min,
      @RequestParam(defaultValue = "4000") float max,
      @RequestParam(defaultValue = "0") int offset,
      @RequestParam(required = false) Integer limit,
      @RequestParam(required = false) String sort) {
    try {
      List<User> userList = userService.getUsers(
          min,
          max,
          offset,
          Optional.ofNullable(limit),
          Optional.ofNullable(sort));
      return new ResponseEntity<GetResponse>(new GetResponse(userList), HttpStatus.OK);
    } catch (IllegalOrderParamException e) {
      System.out.println(e.getMessage());
      return new ResponseEntity<GetResponse>(new GetResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
    }
  }

  @PostMapping(value = "/upload", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<UploadFileResponse> uploadUserData(@RequestParam("file") MultipartFile file) {
    try {
      List<User> userData = userCsvParser.parseCsv(file.getInputStream());
      userService.updateUserDatabase(userData);
      return new ResponseEntity<>(new UploadFileResponse(1), HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity(new UploadFileResponse(0, e.getMessage()), HttpStatus.BAD_REQUEST);
    }
  }
}
