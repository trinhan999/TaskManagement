package com.example.TaskManagement;

import javax.validation.constraints.Size;
import javax.validation.constraints.Min;
import javax.validation.constraints.Max;
import java.util.Date;

enum status {
  TODO,
  IN_PROGRESS,
  DONE
}


public class Task {
  @Size(max =250)
  private String taskName;
  @Size(max =250)
  private String superTaskName;
  @Size(max =500)
  private String description;
  @Min(1)
  @Max(5)
  private Integer taskPoint;
  private status progress;
  @Size(max =30)
  private String AssigneeId;
  private Date startDate;
  private Date endDate;

  @Override
  public String toString() {
    return "Task{" +
        "taskName='" + taskName + '\'' +
        ", superTaskName='" + superTaskName + '\'' +
        ", description='" + description + '\'' +
        ", taskPoint=" + taskPoint +
        ", progress=" + progress +
        ", AssigneeId='" + AssigneeId + '\'' +
        ", startDate=" + startDate +
        ", endDate=" + endDate +
        '}';
  }

  public Task() {
  }

  public Task(String taskName, String description, Integer taskPoint, String assigneeId) {
    this.taskName = taskName;
    this.description = description;
    this.taskPoint = taskPoint;
    this.progress =status.TODO;
    AssigneeId = assigneeId;
  }

  public Task(String taskName, String superTaskName, String description, Integer taskPoint, String assigneeId) {
    this.taskName = taskName;
    this.superTaskName = superTaskName;
    this.description = description;
    this.taskPoint = taskPoint;
    this.progress =status.TODO;
    AssigneeId = assigneeId;
  }

  //region getSet
  public String getTaskName() {
    return taskName;
  }

  public void setTaskName(String taskName) {
    this.taskName = taskName;
  }

  public String getSuperTaskName() {
    return superTaskName;
  }

  public void setSuperTaskName(String superTaskName) {
    this.superTaskName = superTaskName;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Integer getTaskPoint() {
    return taskPoint;
  }

  public void setTaskPoint(Integer taskPoint) {
    this.taskPoint = taskPoint;
  }

  public status getProgress() {
    return progress;
  }

  public void setProgress(status progress) {
    this.progress = progress;
  }

  public String getAssigneeId() {
    return AssigneeId;
  }

  public void setAssigneeId(String assigneeId) {
    AssigneeId = assigneeId;
  }

  public Date getStartDate() {
    return startDate;
  }

  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }

  public Date getEndDate() {
    return endDate;
  }

  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }
  //endregion
}
