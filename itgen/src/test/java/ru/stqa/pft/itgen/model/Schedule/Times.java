package ru.stqa.pft.itgen.model.Schedule;

import java.util.Objects;

public class Times {
  public Integer start;
  public Integer end;

  public Times() {
  }

  public Times withStart(Integer start) {
    this.start = start;
    return this;
  }

  public Times withEnd(Integer end) {
    this.end = end;
    return this;
  }

  @Override
  public String toString() {
    return "Times{" +
            "start=" + start +
            ", end=" + end +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Times times = (Times) o;
    return Objects.equals(start, times.start) &&
            Objects.equals(end, times.end);
  }

  @Override
  public int hashCode() {
    return Objects.hash(start, end);
  }
}
