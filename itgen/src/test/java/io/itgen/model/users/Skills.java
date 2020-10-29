package io.itgen.model.users;

public class Skills {
  private String skills;

  public Skills() {}

  public Skills withSkills(String type) {
    this.skills = type;
    return this;
  }

  public String getSkills() {
    return skills;
  }

  @Override
  public String toString() {
    return "" + skills;
  }
}
