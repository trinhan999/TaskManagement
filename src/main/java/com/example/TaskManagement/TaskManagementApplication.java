package com.example.TaskManagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDate;


@SpringBootApplication
public class TaskManagementApplication {
	public static void main(String[] args) {
		SpringApplication.run(TaskManagementApplication.class, args);
	}
}
