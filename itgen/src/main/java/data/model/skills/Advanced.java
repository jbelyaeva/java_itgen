package data.model.skills;

import java.util.Objects;

public class Advanced {
  private int age;
  private String state;

  public Advanced() {}

  public Advanced withAge(int age) {
    this.age = age;
    return this;
  }

  public Advanced withState(String state) {
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
    Advanced beginner = (Advanced) o;
    return age == beginner.age && state == beginner.state;
  }

  @Override
  public int hashCode() {
    return Objects.hash(age, state);
  }

  @Override
  public String toString() {
    return "Advanced{" + "age=" + age + ", state=" + state + '}';
  }
}
