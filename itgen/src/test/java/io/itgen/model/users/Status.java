package io.itgen.model.users;

import java.util.Objects;

public class Status {

  public String state;

  public Status() {}

  public String getState() {
    return state;
  }

  public Status withState(String state) {
    this.state = state;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Status status = (Status) o;
    return Objects.equals(state, status.state);
  }

  @Override
  public int hashCode() {
    return Objects.hash(state);
  }
}
