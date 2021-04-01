package com.example.TaskManagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class TaskService {
  @Autowired
  JdbcTemplate jdbcTemplate;

  @Autowired
  NamedParameterJdbcTemplate nameJdbc;

  public void deleteTask(String taskName) {
    jdbcTemplate.update("DELETE from Task where task_name = ?", taskName);
  }

  public void addTask(Task task) {
    if (task.getAssigneeId().equals("")) {
      task.setAssigneeId(null);
    }
    String query = "insert into Task(task_name, task_description, task_point, progress, assignee_id) values(?,?,?,?,?)";
    jdbcTemplate.update(query,
        task.getTaskName(),
        task.getDescription(),
        task.getTaskPoint(),
        task.getProgress().toString(),
        task.getAssigneeId());
  }

  public void addSubTask( Task task) {
    if (task.getAssigneeId().equals("")) {
      task.setAssigneeId(null);
    }
    String query = "insert into Task(task_name, sub_task_of, task_description, task_point, progress, assignee_id) " +
        "values(?,?,?,?,?,?)";
    jdbcTemplate.update(query,
        task.getTaskName(),
        task.getSuperTaskName(),
        task.getDescription(),
        task.getTaskPoint(),
        task.getProgress().toString(),
        task.getAssigneeId());
  }

  public void updatePoint(String name, Integer point) {
    String query = "update Task set task_point = ? where task_name like ?";
    jdbcTemplate.update(query, point, name);
  }

  public void updateAssignee(String name, String assigneeId) {
    SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("id", assigneeId);
    int count = nameJdbc.queryForObject("select count(id) from Assignee where id = :id",
        namedParameters, Integer.class);
    if (count == 1) {
      String query = "update Task set assignee_id = ? where task_name like ?";
      jdbcTemplate.update(query, assigneeId, name);
    }

  }

  public void updateProgress(String name, String progress) {
    if (progress.equals("IN_PROGRESS")) {
      String query = "update Task set progress = ?, start_date = ? where task_name like ?";
      jdbcTemplate.update(query, progress, LocalDate.now(), name);
    } else if (progress.equals("DONE")) {
      String query = "update Task set progress = ?, end_date = ? where task_name like ?";
      jdbcTemplate.update(query, progress, LocalDate.now(), name);
    } else {
      String query = "update Task set progress = ?, start_date = NULL, end_date = Null where task_name like ?";
      jdbcTemplate.update(query, progress, name);
    }
  }
}
