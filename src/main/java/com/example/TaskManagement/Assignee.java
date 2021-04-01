package com.example.TaskManagement;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class Assignee {
  @Id
  @NotBlank
  @Size(max = 30)
  private String id;
  @NotBlank
  @Size(max = 100)
  private String firstName;
  @NotBlank
  @Size(max = 100)
  private String lastName;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }
}
