package data.model.skills;

import dev.morphia.annotations.Embedded;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Requirements {
  @Embedded private List<Beginner> beginner = new ArrayList<Beginner>();

  @Embedded private List<Usual> usual = new ArrayList<Usual>();

  @Embedded private List<Advanced> advanced = new ArrayList<Advanced>();

  @Embedded private List<Expert> expert = new ArrayList<Expert>();

  public Requirements() {}

  public Requirements withtBeginner(List<Beginner> beginner) {
    this.beginner = beginner;
    return this;
  }

  public Requirements withUsual(List<Usual> usual) {
    this.usual = usual;
    return this;
  }

  public Requirements withAdvanced(List<Advanced> advanced) {
    this.advanced = advanced;
    return this;
  }

  public Requirements withExpert(List<Expert> expert) {
    this.expert = expert;
    return this;
  }

  public List<Beginner> getBeginner() {
    return beginner;
  }

  public List<Usual> getUsual() {
    return usual;
  }

  public List<Advanced> getAdvanced() {
    return advanced;
  }

  public List<Expert> getExpert() {
    return expert;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Requirements that = (Requirements) o;
    return Objects.equals(beginner, that.beginner)
        && Objects.equals(usual, that.usual)
        && Objects.equals(advanced, that.advanced)
        && Objects.equals(expert, that.expert);
  }

  @Override
  public int hashCode() {
    return Objects.hash(beginner, usual, advanced, expert);
  }

  @Override
  public String toString() {
    return "Requirements{"
        + "beginner="
        + beginner
        + ", usual="
        + usual
        + ", advanced="
        + advanced
        + ", expert="
        + expert
        + '}';
  }
}
