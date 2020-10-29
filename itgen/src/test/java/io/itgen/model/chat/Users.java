package io.itgen.model.chat;

import java.util.Objects;

public class Users {
  private String id;
  private String firstName;
  private String lastName;
  private String engFirstName;
  private String engLastName;
  private String r;
  public Users() { }

  public Users withId(String id) {
    this.id = id;
    return this;
  }

  public Users withFirstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  public Users withLastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  public Users withEngFirstName(String engFirstName) {
    this.engFirstName = engFirstName;
    return this;
  }

  public Users withEngLastName(String engLastName) {
    this.engLastName = engLastName;
    return this;
  }

  public Users withR(String r) {
    this.r = r;
    return this;
  }

  public String getId() {
    return id;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getEngFirstName() {
    return engFirstName;
  }

  public String getEngLastName() {
    return engLastName;
  }

  public String getR() {
    return r;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Users users = (Users) o;
    return Objects.equals(id, users.id) &&
        Objects.equals(firstName, users.firstName) &&
        Objects.equals(lastName, users.lastName) &&
        Objects.equals(engFirstName, users.engFirstName) &&
        Objects.equals(engLastName, users.engLastName) &&
        Objects.equals(r, users.r);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, firstName, lastName, engFirstName, engLastName, r);
  }

  @Override
  public String toString() {
    return "Users{" +
        "id='" + id + '\'' +
        ", firstName='" + firstName + '\'' +
        ", lastName='" + lastName + '\'' +
        ", engFirstName='" + engFirstName + '\'' +
        ", engLastName='" + engLastName + '\'' +
        ", r='" + r + '\'' +
        '}';
  }
}
