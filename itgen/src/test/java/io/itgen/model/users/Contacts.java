package io.itgen.model.users;

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