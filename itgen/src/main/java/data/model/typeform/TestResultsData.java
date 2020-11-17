package data.model.typeform;

import com.google.gson.annotations.Expose;
import dev.morphia.annotations.Embedded;
import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.Property;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity("child-tests-results")
public class TestResultsData {
  @Expose
  @Id
  @Property("_id")
  private String id;

  @Expose
  @Property("childId")
  private String childId;

  @Expose
  @Property("testId")
  private String testId;

  @Expose
  @Property("title")
  private String title;

  @Expose
  @Property("rootFormId")
  private String rootFormId;

  @Expose
  @Property("skillIds")
  private List<String> skillIds = new ArrayList<String>();

  @Expose
  @Property("language")
  private String language;

  @Expose
  @Property("minScore")
  private int minScore;

  @Expose
  @Property("maxScore")
  private int maxScore;

  @Expose
  @Property("createdAt")
  private Date createdAt;

  @Expose
  @Property("rawFormData")
  private String rawFormData;

  @Expose
  @Property("entityTestId")
  private String entityTestId;

  @Embedded private List<Answers> answers = new ArrayList<Answers>();

  @Expose
  @Property("finishedAt")
  private Date finishedAt;

  @Expose
  @Property("finishedSuccess")
  private Boolean finishedSuccess;

  @Expose
  @Property("rawFormAnswers")
  private String rawFormAnswers;

  @Expose
  @Property("score")
  private int score;

  public TestResultsData() {}

  public TestResultsData withId(String id) {
    this.id = id;
    return this;
  }

  public TestResultsData withChildId(String childId) {
    this.childId = childId;
    return this;
  }

  public TestResultsData withTestId(String testId) {
    this.testId = testId;
    return this;
  }

  public TestResultsData withTitle(String title) {
    this.title = title;
    return this;
  }

  public TestResultsData withRootFormId(String rootFormId) {
    this.rootFormId = rootFormId;
    return this;
  }

  public TestResultsData withSkillIds(List<String> skillIds) {
    this.skillIds = skillIds;
    return this;
  }

  public TestResultsData withLanguage(String language) {
    this.language = language;
    return this;
  }

  public TestResultsData withMinScore(int minScore) {
    this.minScore = minScore;
    return this;
  }

  public TestResultsData withMaxScore(int maxScore) {
    this.maxScore = maxScore;
    return this;
  }

  public TestResultsData withCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
    return this;
  }

  public TestResultsData withRawFormData(String rawFormData) {
    this.rawFormData = rawFormData;
    return this;
  }

  public TestResultsData withEntityTestId(String entityTestId) {
    this.entityTestId = entityTestId;
    return this;
  }

  public TestResultsData withAnswers(List<Answers> answers) {
    this.answers = answers;
    return this;
  }

  public TestResultsData withFinishedAt(Date finishedAt) {
    this.finishedAt = finishedAt;
    return this;
  }

  public TestResultsData withFinishedSuccess(Boolean finishedSuccess) {
    this.finishedSuccess = finishedSuccess;
    return this;
  }

  public TestResultsData withRawFormAnswers(String rawFormAnswers) {
    this.rawFormAnswers = rawFormAnswers;
    return this;
  }

  public TestResultsData withScore(int score) {
    this.score = score;
    return this;
  }

  public String getId() {
    return id;
  }

  public String getChildId() {
    return childId;
  }

  public String getTestId() {
    return testId;
  }

  public String getTitle() {
    return title;
  }

  public String getRootFormId() {
    return rootFormId;
  }

  public List<String> getSkillIds() {
    return skillIds;
  }

  public String getLanguage() {
    return language;
  }

  public int getMinScore() {
    return minScore;
  }

  public int getMaxScore() {
    return maxScore;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public String getRawFormData() {
    return rawFormData;
  }

  public String getEntityTestId() {
    return entityTestId;
  }

  public List<Answers> getAnswers() {
    return answers;
  }

  public Date getFinishedAt() {
    return finishedAt;
  }

  public Boolean getFinishedSuccess() {
    return finishedSuccess;
  }

  public String getRawFormAnswers() {
    return rawFormAnswers;
  }

  public int getScore() {
    return score;
  }

  @Override
  public String toString() {
    return "TestResultsData{" +
        "id='" + id + '\'' +
        ", childId='" + childId + '\'' +
        ", testId='" + testId + '\'' +
        ", title='" + title + '\'' +
        ", rootFormId='" + rootFormId + '\'' +
        ", skillIds=" + skillIds +
        ", language='" + language + '\'' +
        ", minScore=" + minScore +
        ", maxScore=" + maxScore +
        ", createdAt=" + createdAt +
        ", rawFormData='" + rawFormData + '\'' +
        ", entityTestId='" + entityTestId + '\'' +
        ", answers=" + answers +
        ", finishedAt=" + finishedAt +
        ", finishedSuccess=" + finishedSuccess +
        ", rawFormAnswers='" + rawFormAnswers + '\'' +
        ", score=" + score +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TestResultsData that = (TestResultsData) o;
    return minScore == that.minScore
        && maxScore == that.maxScore
        && score == that.score
        && Objects.equals(id, that.id)
        && Objects.equals(childId, that.childId)
        && Objects.equals(testId, that.testId)
        && Objects.equals(title, that.title)
        && Objects.equals(rootFormId, that.rootFormId)
        && Objects.equals(skillIds, that.skillIds)
        && Objects.equals(language, that.language)
        && Objects.equals(createdAt, that.createdAt)
        && Objects.equals(rawFormData, that.rawFormData)
        && Objects.equals(answers, that.answers)
        && Objects.equals(finishedAt, that.finishedAt)
        && Objects.equals(finishedSuccess, that.finishedSuccess)
        && Objects.equals(rawFormAnswers, that.rawFormAnswers);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        id,
        childId,
        testId,
        title,
        rootFormId,
        skillIds,
        language,
        minScore,
        maxScore,
        createdAt,
        rawFormData,
        answers,
        finishedAt,
        finishedSuccess,
        rawFormAnswers,
        score);
  }
}
