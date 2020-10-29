package io.itgen.model.schedule;

import java.util.Objects;

public class ST {
  public Double s;
  public Double e;

  public ST() {}

  public ST withS(Double s) {
    this.s = s;
    return this;
  }

  public ST withE(Double e) {
    this.e = e;
    return this;
  }

  @Override
  public String toString() {
    return "ST{" + "s=" + s + ", e=" + e + '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ST st = (ST) o;
    return Objects.equals(s, st.s) && Objects.equals(e, st.e);
  }

  @Override
  public int hashCode() {
    return Objects.hash(s, e);
  }
}
