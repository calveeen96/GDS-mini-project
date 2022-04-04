package com.example.GDS.mini.project;

import com.example.GDS.mini.project.Exceptions.InvalidNumberOfColumnsException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@SpringBootTest
class UserCsvParserTest {

	@Autowired
	UserCsvParser userCsvParser;


	@Test
	void parseCsv_SingleRow_Success() throws Exception {
		InputStream stubInputStream = new ByteArrayInputStream(
				"Name,Salary\nJohn Doe,100\n".getBytes(StandardCharsets.UTF_8));
		List<User> users = userCsvParser.parseCsv(stubInputStream);

		assertThat(users.get(0).getName()).isEqualTo("John Doe");
		assertThat(users.get(0).getSalary()).isEqualTo(100);
	}

	@Test
	void parseCsv_MultipleRows_Success() throws Exception {
		InputStream stubInputStream = new ByteArrayInputStream(
				"Name,Salary\nJohn Doe,100\nHelen Keller,5000".getBytes(StandardCharsets.UTF_8));
		List<User> users = userCsvParser.parseCsv(stubInputStream);

		assertThat(users.size()).isEqualTo(2);
		assertThat(users.get(0).getName()).isEqualTo("John Doe");
		assertThat(users.get(0).getSalary()).isEqualTo(100);
		assertThat(users.get(1).getName()).isEqualTo("Helen Keller");
		assertThat(users.get(1).getSalary()).isEqualTo(5000);
	}

	@Test
	void parseCsv_IgnoreUsersWithSalaryLessThanZero_Success() throws Exception {
		InputStream stubInputStream = new ByteArrayInputStream(
				"Name,Salary\nJohn Doe,-5\nHelen Keller,5000\nJohnD,-2".getBytes(StandardCharsets.UTF_8));

		List<User> users = userCsvParser.parseCsv(stubInputStream);

		assertThat(users.size()).isEqualTo(1);
		assertThat(users.get(0).getName()).isEqualTo("Helen Keller");
		assertThat(users.get(0).getSalary()).isEqualTo(5000);
	}

	@Test
	void parseCsv_MultipleRows_Throws_InvalidNumberOfColumns() {
		InputStream stubInputStream = new ByteArrayInputStream(
				"Name,Salary\nJohn Doe,100\nHelen Keller,5000, 101, 200".getBytes(StandardCharsets.UTF_8));

		assertThatThrownBy(() -> userCsvParser.parseCsv(stubInputStream))
				.isInstanceOf(InvalidNumberOfColumnsException.class)
				.hasMessageContaining("Expected 2 columns in csv file but found 4 instead");
	}
}
