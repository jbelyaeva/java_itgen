package ru.stqa.pft.itgen.model;

import javax.persistence.Embeddable;

@Embeddable
public class ST {
  public Double s;
  public Double e;

  public ST(Double s, Double e) {
    this.s = s;
    this.e = e;
  }

  public ST() {
  }

  public Double getS() {
    return s;
  }

  public void setS(Double s) {
    this.s = s;
  }

  public Double getE() {
    return e;
  }

  public void setE(Double e) {
    this.e = e;
  }

  @Override
  public String toString() {
    return "ST{" +
            "s=" + s +
            ", e=" + e +
            '}';
  }
}

