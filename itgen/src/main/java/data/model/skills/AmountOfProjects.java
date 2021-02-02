package data.model.skills;

import java.util.Objects;

public class AmountOfProjects {

  private int p25;
  private int p50;
  private int p75;

  public AmountOfProjects() {
  }

  public AmountOfProjects withP25(int p25) {
    this.p25 = p25;
    return this;
  }

  public AmountOfProjects withP50(int p50) {
    this.p50 = p50;
    return this;
  }

  public AmountOfProjects withP75(int p75) {
    this.p75 = p75;
    return this;
  }

  public int getP25() {
    return p25;
  }

  public int getP50() {
    return p50;
  }

  public int getP75() {
    return p75;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AmountOfProjects that = (AmountOfProjects) o;
    return p25 == that.p25 && p50 == that.p50 && p75 == that.p75;
  }

  @Override
  public int hashCode() {
    return Objects.hash(p25, p50, p75);
  }

  @Override
  public String toString() {
    return "AmountOfProjects{" +
        "p25=" + p25 +
        ", p50=" + p50 +
        ", p75=" + p75 +
        '}';
  }
}
