package data.model.usersGeneral;

import java.util.Objects;

public class FinishedLessonsCountBySkill {

  public int one;
  public int count;
  public int minutes;

  public FinishedLessonsCountBySkill() {
  }

  public int getOne() {
    return one;
  }

  public int getCount() {
    return count;
  }

  public int getMinutes() {
    return minutes;
  }

  public FinishedLessonsCountBySkill withOne(int one) {
    this.one = one;
    return this;
  }

  public FinishedLessonsCountBySkill withCount(int count) {
    this.count = count;
    return this;
  }

  public FinishedLessonsCountBySkill withMinutes(int minutes) {
    this.minutes = minutes;
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
    return one == that.one && count == that.count && minutes == that.minutes;
  }

  @Override
  public int hashCode() {
    return Objects.hash(one, count, minutes);
  }

  @Override
  public String toString() {
    return "FinishedLessonsCountBySkill{" +
        "one=" + one +
        ", count=" + count +
        ", minutes=" + minutes +
        '}';
  }
}
