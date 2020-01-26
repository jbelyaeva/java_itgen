package ru.stqa.pft.itgen.model;

public class WorkerUserData {
  private final String firstname;
  private final String lastname;
  private final String email;
  private final String phone;
  private final String role;

  public WorkerUserData(String firstname, String lastname, String email, String phone, String role) {

    this.firstname = firstname;
    this.lastname = lastname;
    this.email = email;
    this.phone = phone;
    this.role = role;
  }

  public String getFirstname() {
    return firstname;
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

}
