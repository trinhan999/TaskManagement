package com.example.TaskManagement;

import liquibase.pro.packaged.S;
import org.hibernate.annotations.Check;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.validation.Valid;
import javax.validation.constraints.*;

@Validated
@RestController
@RequestMapping("task")
public class TaskController implements WebMvcConfigurer {
  @Autowired
  TaskService taskService;

  @PutMapping("/point")
  public String updatePoint(@RequestParam String name, @RequestParam @Min(1) @Max(5) Integer point) {
    taskService.updatePoint(name, point);
    return "update point";
  }

  @PutMapping("/progress")
  public String updateProgress(@RequestParam String name, @Pattern(regexp = "TODO|IN_PROGRESS|DONE") String progress) {
    taskService.updateProgress(name, progress);
    return "updated";
  }

  @PutMapping("/assignee")
  public String updateAssignee(@RequestParam String name, String assignee) {
    taskService.updateAssignee(name, assignee);
    return "updated";
  }

  @PostMapping
  public String add (@RequestParam @NotBlank @Size(max = 250) String name,
                     @RequestParam @Size(max = 500) String des,
                     @RequestParam @Min(1) @Max(5) Integer point,
                     @RequestParam @Size(max = 30) String assignee){
    Task t = new Task(name,des,point,assignee);
    taskService.addTask(t);
    return "added";
  }

  @PostMapping("/sub")
  public String addSub (@RequestParam @NotBlank @Size(max = 250) String name,
                        @RequestParam @NotBlank @Size(max = 250) String superName,
                        @RequestParam @Size(max = 500) String des,
                        @RequestParam @Min(1) @Max(5) Integer point,
                        @RequestParam @Size(max = 30)  String assignee){
    Task t = new Task(name,superName,des,point,assignee);
    taskService.addSubTask(t);
    return "added sub";
  }

  @DeleteMapping
  public String delete(@RequestParam String name) {
    taskService.deleteTask(name);
    return "deleted";
  }

  @PostMapping("/test")
  public String test(@Valid @RequestBody Task task) {
    return task.toString();
  }
}
