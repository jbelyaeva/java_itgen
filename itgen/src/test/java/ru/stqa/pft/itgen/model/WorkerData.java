package ru.stqa.pft.itgen.model;

public class WorkerData {
  private final String lastname;
  private final String email;
  private final String phone;
  private final String role;
  private final String firstname;

  public WorkerData(String lastname, String email, String phone, String role, String firstname) {
    this.lastname = lastname;
    this.email = email;
    this.phone = phone;
    this.role = role;
    this.firstname = firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public String getEmail() {
    return email;
  }

  public String getPhone() {
    return phone;
  }

  public String getRole() {
    return role;
  }

  public String getFirstname() {
    return firstname;
  }
}
