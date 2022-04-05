package com.example.GDS.mini.project;

import com.example.GDS.mini.project.Exceptions.InvalidNumberOfColumnsException;
import com.example.GDS.mini.project.Exceptions.SalaryNotParsableException;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserCsvParser {


  public List<User> parseCsv(InputStream stream) throws SalaryNotParsableException,
      InvalidNumberOfColumnsException, IOException, CsvValidationException {
    List<User> userData = new ArrayList<>();
    try {
      CSVReader csvReader = new CSVReader(new BufferedReader(new InputStreamReader(stream)));
      csvReader.readNext();

      String[] nextRow = null;
      while ((nextRow = csvReader.readNext()) != null) {
        if (nextRow.length != 2) {
          throw new InvalidNumberOfColumnsException(
              "Expected 2 columns in csv file but found " + nextRow.length + " instead"
          );
        }

        String name = nextRow[0];
        float salary = Float.parseFloat(nextRow[1]);

        if (salary >= 0) {
          User user = new User(name, salary);
          userData.add(user);
        }
      }
    } catch (NumberFormatException e) {
      throw new SalaryNotParsableException("Expected salary to be of type float");
    }
    return userData;
  }
}
