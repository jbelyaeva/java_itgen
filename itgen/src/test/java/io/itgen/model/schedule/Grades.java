package io.itgen.model.schedule;

import java.util.Objects;

public class Grades {
  public Integer completeHw;
  public Integer learnedNewTopic;
  public Integer completeTasks;
  public Integer madeNoMistakes;
  public Integer wasInattentive;
  public Integer hasDesireToLearn;

  public Grades() {
  }

  public Grades withCompleteHw(Integer completeHw) {
    this.completeHw = completeHw;
    return this;
  }

  public Grades withLearnedNewTopic(Integer learnedNewTopic) {
    this.learnedNewTopic = learnedNewTopic;
    return this;
  }

  public Grades withCompleteTasks(Integer completeTasks) {
    this.completeTasks = completeTasks;
    return this;
  }

  public Grades withMadeNoMistakes(Integer madeNoMistakes) {
    this.madeNoMistakes = madeNoMistakes;
    return this;
  }

  public Grades withWasInattentive(Integer wasInattentive) {
    this.wasInattentive = wasInattentive;
    return this;
  }

  public Grades withHasDesireToLearn(Integer hasDesireToLearn) {
    this.hasDesireToLearn = hasDesireToLearn;
    return this;
  }

  public Integer getCompleteHw() {
    return completeHw;
  }

  public Integer getLearnedNewTopic() {
    return learnedNewTopic;
  }

  public Integer getCompleteTasks() {
    return completeTasks;
  }

  public Integer getMadeNoMistakes() {
    return madeNoMistakes;
  }

  public Integer getWasInattentive() {
    return wasInattentive;
  }

  public Integer getHasDesireToLearn() {
    return hasDesireToLearn;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Grades grades = (Grades) o;
    return Objects.equals(completeHw, grades.completeHw) &&
        Objects.equals(learnedNewTopic, grades.learnedNewTopic) &&
        Objects.equals(completeTasks, grades.completeTasks) &&
        Objects.equals(madeNoMistakes, grades.madeNoMistakes) &&
        Objects.equals(wasInattentive, grades.wasInattentive) &&
        Objects.equals(hasDesireToLearn, grades.hasDesireToLearn);
  }

  @Override
  public int hashCode() {
    return Objects.hash(completeHw, learnedNewTopic, completeTasks, madeNoMistakes, wasInattentive,
        hasDesireToLearn);
  }

  @Override
  public String toString() {
    return "Grades{" +
        "completeHw=" + completeHw +
        ", learnedNewTopic=" + learnedNewTopic +
        ", completeTasks=" + completeTasks +
        ", madeNoMistakes=" + madeNoMistakes +
        ", wasInattentive=" + wasInattentive +
        ", hasDesireToLearn=" + hasDesireToLearn +
        '}';
  }
}
