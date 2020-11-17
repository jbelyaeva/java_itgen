package data.model.typeform;

import com.google.gson.annotations.Expose;
import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.Property;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity("child-tests")
public class TestData {
  @Expose
  @Id
  @Property("_id")
  private String id;

  @Expose
  @Property("title")
  private String title;

  @Expose
  @Property("rootFormId")
  private String rootFormId;

  @Expose
  @Property("language")
  private String language;

  @Expose
  @Property("description")
  private String description;

  @Property("skillIds")
  @Expose
  private List<String> skillIds = new ArrayList<String>();

  @Expose
  @Property("minScore")
  private int minScore;

  @Expose
  @Property("maxScore")
  private int maxScore;

  @Expose
  @Property("timeForPassing")
  private int timeForPassing;

  @Expose
  @Property("createdAt")
  private Date createdAt;

  @Expose
  @Property("removedAt")
  private Date removedAt;

  @Expose
  @Property("entityTestId")
  private String entityTestId;

  @Expose
  @Property("updatedAt")
  private Date updatedAt;

  public TestData() {}

  public TestData withId(String id) {
    this.id = id;
    return this;
  }

  public TestData withTitle(String title) {
    this.title = title;
    return this;
  }

  public TestData withRootFormId(String rootFormId) {
    this.rootFormId = rootFormId;
    return this;
  }

  public TestData withLanguage(String language) {
    this.language = language;
    return this;
  }

  public TestData withDescription(String description) {
    this.description = description;
    return this;
  }

  public TestData withSkillIds(List<String> skillIds) {
    this.skillIds = skillIds;
    return this;
  }

  public TestData withMinScore(int minScore) {
    this.minScore = minScore;
    return this;
  }

  public TestData withMaxScore(int maxScore) {
    this.maxScore = maxScore;
    return this;
  }

  public TestData withTimeForPassing(int timeForPassing) {
    this.timeForPassing = timeForPassing;
    return this;
  }

  public TestData withRemovedAt(Date removedAt) {
    this.removedAt = removedAt;
    return this;
  }

  public TestData withCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
    return this;
  }

  public TestData withEntityTestId(String entityTestId) {
    this.entityTestId = entityTestId;
    return this;
  }

  public TestData withUpdatedAt(Date updatedAt) {
    this.updatedAt = updatedAt;
    return this;
  }

  public String getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public String getRootFormId() {
    return rootFormId;
  }

  public String getLanguage() {
    return language;
  }

  public String getDescription() {
    return description;
  }

  public List<String> getSkillIds() {
    return skillIds;
  }

  public int getMinScore() {
    return minScore;
  }

  public int getMaxScore() {
    return maxScore;
  }

  public int getTimeForPassing() {
    return timeForPassing;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public String getEntityTestId() {
    return entityTestId;
  }

  public Date getUpdatedAt() {
    return updatedAt;
  }

  @Override
  public String toString() {
    return "TestData{"
        + "id='"
        + id
        + '\''
        + ", title='"
        + title
        + '\''
        + ", rootFormId='"
        + rootFormId
        + '\''
        + ", language='"
        + language
        + '\''
        + ", description='"
        + description
        + '\''
        + ", skillIds="
        + skillIds
        + ", minScore="
        + minScore
        + ", maxScore="
        + maxScore
        + ", timeForPassing="
        + timeForPassing
        + ", createdAt="
        + createdAt
        + ", entityTestId='"
        + entityTestId
        + '\''
        + ", updatedAt="
        + updatedAt
        + ", removedAt="
        + removedAt
        + '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TestData testData = (TestData) o;
    return minScore == testData.minScore
        && maxScore == testData.maxScore
        && timeForPassing == testData.timeForPassing
        && Objects.equals(id, testData.id)
        && Objects.equals(title, testData.title)
        && Objects.equals(rootFormId, testData.rootFormId)
        && Objects.equals(language, testData.language)
        && Objects.equals(description, testData.description)
        && Objects.equals(skillIds, testData.skillIds);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        id, title, rootFormId, language, description, skillIds, minScore, maxScore, timeForPassing);
  }
}
