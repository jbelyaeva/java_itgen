package data.model.usersGeneral;

import dev.morphia.annotations.Property;
import java.util.Objects;

public class FinishedLessonsCountBySkill {
  @Property("1")
  public int one;

  public FinishedLessonsCountBySkill() {}

  public int getOne() {
    return one;
  }

  public FinishedLessonsCountBySkill withOne(int one) {
    this.one = one;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    FinishedLessonsCountBySkill that = (FinishedLessonsCountBySkill) o;
    return one == that.one;
  }

  @Override
  public int hashCode() {
    return Objects.hash(one);
  }
}
