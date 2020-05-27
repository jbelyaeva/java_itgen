package ru.stqa.pft.itgen.model.users;

import ru.stqa.pft.itgen.model.StudentData;

import javax.persistence.Embeddable;


public class Contacts {
  private String type;
  private String val;

  public Contacts() {
  }

  public Contacts withType(String type) {
    this.type = type;
    return this;
  }

  public Contacts withVal(String val) {
    this.val = val;
    return this;
  }

  public String getType() {
    return type;
  }

  public String getVal() {
    return val;
  }

  @Override
  public String toString() {
    return "Contacts{" +
            "type='" + type + '\'' +
            ", val='" + val + '\'' +
            '}';
  }
}