package data.model.skills;

import com.google.gson.annotations.Expose;
import dev.morphia.annotations.Embedded;
import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.Property;
import java.util.Objects;

@Entity("skills")
public class SkillsData {

  @Expose
  @Id
  @Property("_id")
  private String id;

  @Expose
  @Property("lang")
  private String lang;

  @Expose
  @Property("title")
  private String title;

  @Expose
  @Property("visibility")
  private String visibility;

  @Expose
  @Property("desc")
  private String desc;

  @Property("minAge")
  @Expose
  private int minAge;

  @Expose
  @Property("skillId")
  private String skillId;

  @Expose
  @Property("testId")
  private String testId;

  @Expose
  @Property("minAgeWithoutTest")
  private int minAgeWithoutTest;

  @Expose
  @Property("needRemindAboutLesson")
  private Boolean needRemindAboutLesson;

  @Expose
  @Property("downloadLink")
  private String downloadLink;

  @Expose
  @Property("payCoefficient")
  private int payCoefficient;

  @Expose
  @Property("trialDuration")
  private int trialDuration;

  @Embedded
  private Requirements requirements;

  public SkillsData() {
  }

  public SkillsData withId(String id) {
    this.id = id;
    return this;
  }

  public SkillsData withLang(String lang) {
    this.lang = lang;
    return this;
  }

  public SkillsData withTitle(String title) {
    this.title = title;
    return this;
  }

  public SkillsData withVisibility(String visibility) {
    this.visibility = visibility;
    return this;
  }

  public SkillsData withDesc(String desc) {
    this.desc = desc;
    return this;
  }

  public SkillsData withMinAge(int minAge) {
    this.minAge = minAge;
    return this;
  }

  public SkillsData withSkillId(String skillId) {
    this.skillId = skillId;
    return this;
  }

  public SkillsData withTestId(String testId) {
    this.testId = testId;
    return this;
  }

  public SkillsData withMinAgeWithoutTest(int minAgeWithoutTest) {
    this.minAgeWithoutTest = minAgeWithoutTest;
    return this;
  }

  public SkillsData withNeedRemindAboutLesson(Boolean needRemindAboutLesson) {
    this.needRemindAboutLesson = needRemindAboutLesson;
    return this;
  }

  public SkillsData withDownloadLink(String downloadLink) {
    this.downloadLink = downloadLink;
    return this;
  }

  public SkillsData withRequirements(Requirements requirements) {
    this.requirements = requirements;
    return this;
  }

  public SkillsData withPayCoefficient(int payCoefficient) {
    this.payCoefficient = payCoefficient;
    return this;
  }

  public SkillsData withtTrialDuration(int trialDuration) {
    this.trialDuration = trialDuration;
    return this;
  }

  public String getId() {
    return id;
  }

  public String getLang() {
    return lang;
  }

  public String getTitle() {
    return title;
  }

  public String getVisibility() {
    return visibility;
  }

  public String getDesc() {
    return desc;
  }

  public int getMinAge() {
    return minAge;
  }

  public String getSkillId() {
    return skillId;
  }

  public String getTestId() {
    return testId;
  }

  public int getMinAgeWithoutTest() {
    return minAgeWithoutTest;
  }

  public Boolean getNeedRemindAboutLesson() {
    return needRemindAboutLesson;
  }

  public String getDownloadLink() {
    return downloadLink;
  }

  public Requirements getRequirements() {
    return requirements;
  }

  public int getPayCoefficient() {
    return payCoefficient;
  }

  public int getTrialDuration() {
    return trialDuration;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SkillsData that = (SkillsData) o;
    return minAge == that.minAge
        && minAgeWithoutTest == that.minAgeWithoutTest
        && Objects.equals(id, that.id)
        && Objects.equals(lang, that.lang)
        && Objects.equals(title, that.title)
        && Objects.equals(visibility, that.visibility)
        && Objects.equals(desc, that.desc)
        && Objects.equals(skillId, that.skillId)
        && Objects.equals(testId, that.testId)
        && Objects.equals(needRemindAboutLesson, that.needRemindAboutLesson)
        && Objects.equals(downloadLink, that.downloadLink)
        && Objects.equals(requirements, that.requirements);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, lang, title, visibility, desc, minAge, skillId, testId,
        minAgeWithoutTest, needRemindAboutLesson, downloadLink, requirements);
  }

  @Override
  public String toString() {
    return "SkillsData{" +
        "id='" + id + '\'' +
        ", lang='" + lang + '\'' +
        ", title='" + title + '\'' +
        ", visibility='" + visibility + '\'' +
        ", desc='" + desc + '\'' +
        ", minAge=" + minAge +
        ", skillId='" + skillId + '\'' +
        ", testId='" + testId + '\'' +
        ", minAgeWithoutTest=" + minAgeWithoutTest +
        ", needRemindAboutLesson=" + needRemindAboutLesson +
        ", downloadLink='" + downloadLink + '\'' +
        ", requirements=" + requirements +
        '}';
  }
}
