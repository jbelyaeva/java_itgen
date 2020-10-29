package io.itgen.model.skills;

import java.util.Objects;

public class Expert {
  private int age;
  private String state;

  public Expert() {}

  public Expert withAge(int age) {
    this.age = age;
    return this;
  }

  public Expert withState(String state) {
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
    Expert beginner = (Expert) o;
    return age == beginner.age && state == beginner.state;
  }

  @Override
  public int hashCode() {
    return Objects.hash(age, state);
  }

  @Override
  public String toString() {
    return "Expert{" + "age=" + age + ", state=" + state + '}';
  }
}
