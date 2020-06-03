package ru.stqa.pft.itgen.model.tasks;

import java.util.Objects;

public class Lesson {
  public String sId;
  public Double w;
  public Double st;

  public Lesson() {
  }

  public Lesson withSId(String sId) {
    this.sId = sId;
    return this;
  }

  public Lesson withW(Double w) {
    this.w = w;
    return this;
  }

  public Lesson withSt(Double st) {
    this.st = st;
    return this;
  }

  @Override
  public String toString() {
    return "Lesson{" +
            "sId=" + sId +
            ", w=" + w +
            ", st=" + st +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Lesson lesson = (Lesson) o;
    return Objects.equals(sId, lesson.sId) &&
            Objects.equals(w, lesson.w) &&
            Objects.equals(st, lesson.st);
  }

  @Override
  public int hashCode() {
    return Objects.hash(sId, w, st);
  }
}
