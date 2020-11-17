package data.model.typeform;

import java.util.Objects;

public class Answers {

  private String title;
  private String learnerAnswers;
  private String trueAnswers;
  private Boolean isAnswerTrue;

  public Answers() {}

  public Answers withTitle(String title) {
    this.title = title;
    return this;
  }

  public Answers withLearnerAnswers(String learnerAnswers) {
    this.learnerAnswers = learnerAnswers;
    return this;
  }

  public Answers withTrueAnswers(String trueAnswers) {
    this.trueAnswers = trueAnswers;
    return this;
  }

  public Answers withIsAnswerTrue(Boolean isAnswerTrue) {
    this.isAnswerTrue = isAnswerTrue;
    return this;
  }

  public String getTitle() {
    return title;
  }

  public String getLearnerAnswers() {
    return learnerAnswers;
  }

  public String getTrueAnswers() {
    return trueAnswers;
  }

  public Boolean getAnswerTrue() {
    return isAnswerTrue;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Answers answers = (Answers) o;
    return Objects.equals(title, answers.title)
        && Objects.equals(learnerAnswers, answers.learnerAnswers)
        && Objects.equals(trueAnswers, answers.trueAnswers)
        && Objects.equals(isAnswerTrue, answers.isAnswerTrue);
  }

  @Override
  public int hashCode() {
    return Objects.hash(title, learnerAnswers, trueAnswers, isAnswerTrue);
  }

  @Override
  public String toString() {
    return "Answers{"
        + "title='"
        + title
        + '\''
        + ", learnerAnswers='"
        + learnerAnswers
        + '\''
        + ", trueAnswers='"
        + trueAnswers
        + '\''
        + ", isAnswerTrue="
        + isAnswerTrue
        + '}';
  }
}
