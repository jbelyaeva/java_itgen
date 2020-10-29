package io.itgen.model.skills;

import java.util.Objects;

public class Beginner {
  private int age;
  private String state;

  public Beginner() {}

  public Beginner withAge(int age) {
    this.age = age;
    return this;
  }

  public Beginner withState(String state) {
    this.state = state;
    return this;
  }

  public String getState() {
    return state;
  }

  public int getAge() {
    return age;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Beginner beginner = (Beginner) o;
    return age == beginner.age && state == beginner.state;
  }

  @Override
  public int hashCode() {
    return Objects.hash(age, state);
  }

  @Override
  public String toString() {
    return "Beginner{" + "age=" + age + ", state=" + state + '}';
  }
}
