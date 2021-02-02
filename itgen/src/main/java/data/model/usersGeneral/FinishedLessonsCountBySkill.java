package data.model.usersGeneral;

import dev.morphia.annotations.Embedded;
import java.util.Objects;

public class FinishedLessonsCountBySkill {

  @Embedded("1")
  public One ones;
  @Embedded("21")
  public TwentyOne twentyOnes;

  public FinishedLessonsCountBySkill() {
  }


  public One getOnes() {
    return ones;
  }

  public TwentyOne getTwentyOnes() {
    return twentyOnes;
  }

  public FinishedLessonsCountBySkill withOnes(One ones) {
    this.ones = ones;
    return this;
  }

  public FinishedLessonsCountBySkill withTwentyOnes(TwentyOne twentyOnes) {
    this.twentyOnes = twentyOnes;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FinishedLessonsCountBySkill that = (FinishedLessonsCountBySkill) o;
    return Objects.equals(ones, that.ones) && Objects.equals(twentyOnes,
        that.twentyOnes);
  }

  @Override
  public int hashCode() {
    return Objects.hash(ones, twentyOnes);
  }

  @Override
  public String toString() {
    return "FinishedLessonsCountBySkill{" +
        "ones=" + ones +
        ", twentyOnes=" + twentyOnes +
        '}';
  }
}
