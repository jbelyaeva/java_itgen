package io.itgen.model.schedule;

import dev.morphia.annotations.Embedded;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FinishedSlots {
  private Double w;
  private String id;
  @Embedded private ST st;
  @Embedded private List<C> c = new ArrayList<C>();

  private Double startedAt;
  private Double finishedAt;

  public FinishedSlots() {}

  public FinishedSlots withW(Double w) {
    this.w = w;
    return this;
  }

  public FinishedSlots withId(String id) {
    this.id = id;
    return this;
  }

  public FinishedSlots withSt(ST st) {
    this.st = st;
    return this;
  }

  public FinishedSlots withC(List<C> c) {
    this.c = c;
    return this;
  }

  public FinishedSlots withStartedAt(Double startedAt) {
    this.startedAt = startedAt;
    return this;
  }

  public FinishedSlots withFinishedAt(Double finishedAt) {
    this.finishedAt = finishedAt;
    return this;
  }

  public Double getW() {
    return w;
  }

  public String getId() {
    return id;
  }

  public ST getSt() {
    return st;
  }

  public List<C> getC() {
    return c;
  }

  public Double getStartedAt() {
    return startedAt;
  }

  public Double getFinishedAt() {
    return finishedAt;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    FinishedSlots slots = (FinishedSlots) o;
    return Objects.equals(w, slots.w)
        && Objects.equals(id, slots.id)
        && Objects.equals(st, slots.st)
        && Objects.equals(c, slots.c);
  }

  @Override
  public int hashCode() {
    return Objects.hash(w, id, st, c);
  }

  @Override
  public String toString() {
    return "Slots{" + "w=" + w + ", id='" + id + '\'' + ", st=" + st + ", c=" + c + '}';
  }
}
