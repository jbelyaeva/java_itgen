package io.itgen.model.requests;

public class Times {
  public Double min;
  public Double max;

  public Times() {}

  public Times withMin(Double min) {
    this.min = min;
    return this;
  }

  public Times withMax(Double max) {
    this.max = max;
    return this;
  }

  public Double getMin() {
    return min;
  }

  public Double getMax() {
    return max;
  }

  @Override
  public String toString() {
    return "Times{" + "min=" + min + ", max=" + max + '}';
  }
}
