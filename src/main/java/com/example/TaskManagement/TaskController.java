package com.example.TaskManagement;

import liquibase.pro.packaged.S;
import org.hibernate.annotations.Check;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.validation.Valid;
import javax.validation.constraints.*;

@RestController
public class TaskController implements WebMvcConfigurer {
  @Autowired
  TaskService taskService;

  @PutMapping("/task/point")
  public String updatePoint(@RequestParam String name, @RequestParam @Min(1) @Max(5) Integer point) {
    taskService.updatePoint(name, point);
    return "update point";
  }

  @PutMapping("/task/progress")
  public String updateProgress(@RequestParam String name, @Pattern(regexp = "TODO|IN_PROGRESS|DONE") String progress) {
    taskService.updateProgress(name, progress);
    return "updated";
  }

  @PutMapping("/task/assignee")
  public String updateAssignee(@RequestParam String name, String assignee) {
    taskService.updateAssignee(name, assignee);
    return "updated";
  }

  @PostMapping("/task")
  public String add (@RequestBody @Valid Task task, BindingResult bindingResult){
    taskService.addTask(task);
    return "added";
  }

  @PostMapping("/task/sub")
  public String addSub (@RequestBody @Valid Task task, BindingResult bindingResult){
    taskService.addSubTask(task);
    return "added sub";
  }

  @DeleteMapping("/task")
  public String delete(@RequestParam String name) {
    taskService.deleteTask(name);
    return "deleted";
  }

  @PostMapping("/task/test")
  public String test(@Valid @RequestBody Task task) {
    return task.toString();
  }
}
