package ru.stqa.pft.itgen.model.Schedule;

import dev.morphia.annotations.Embedded;
import dev.morphia.annotations.PostLoad;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Slots {
  private Double w;
  private String id;
  @Embedded
  private ST st;
  @Embedded
  private List<C> c = new ArrayList<C>();
  private Boolean blocked;
  private String blockDesc;
  private Boolean cancelled;
  private Double cancelledAt;

  public Slots() {
  }

  public Slots withW(Double w) {
    this.w = w;
    return this;
  }

  public Slots withId(String id) {
    this.id = id;
    return this;
  }

  public Slots withSt(ST st) {
    this.st = st;
    return this;
  }

  public Slots withC(List<C> c) {
    this.c = c;
    return this;
  }

  public Slots withBlocked(Boolean blocked) {
    this.blocked = blocked;
    return this;
  }

  public Slots withBlockDesc(String blockDesc) {
    this.blockDesc = blockDesc;
    return this;
  }

  public Slots withCancelled (Boolean cancelled) {
    this.cancelled = cancelled;
    return this;
  }

  public Slots withCancelledAt(Double cancelledAt) {
    this.cancelledAt =cancelledAt;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Slots slots = (Slots) o;
    return Objects.equals(w, slots.w) &&
            Objects.equals(id, slots.id) &&
            Objects.equals(st, slots.st) &&
            Objects.equals(c, slots.c) &&
            Objects.equals(blocked, slots.blocked) &&
            Objects.equals(blockDesc, slots.blockDesc) &&
            Objects.equals(cancelled, slots.cancelled);
  }

  @Override
  public int hashCode() {
    return Objects.hash(w, id, st, c, blocked, blockDesc, cancelled);
  }

  @Override
  public String toString() {
    return "Slots{" +
            "w=" + w +
            ", id='" + id + '\'' +
            ", st=" + st +
            ", c=" + c +
            ", blocked=" + blocked +
            ", blockDesc='" + blockDesc + '\'' +
            ", cancelled=" + cancelled +
            ", cancelledAt=" + cancelledAt +
            '}';
  }
}
