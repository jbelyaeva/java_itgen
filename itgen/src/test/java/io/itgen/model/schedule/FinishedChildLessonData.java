package io.itgen.model.schedule;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.Property;
import java.util.Objects;

@Entity("finished-childLessons")
public class FinishedChildLessonData {
  @Id
  @Property("_id")
  private String id;

  @Property("scheduleId")
  private String scheduleId;

  @Property("week")
  private Double week;

  @Property("type")
  private int type;

  @Property("trainerId")
  private String trainerId;

  @Property("childId")
  private String childId;

  @Property("status")
  private String status;

  @Property("score")
  private int score;

  @Property("duration")
  private int duration;

  @Property("skillId")
  private String skillId;

  @Property("lang")
  private String lang;

  @Property("rating")
  private int rating;

  @Property("isTrail")
  private Boolean isTrail;

  @Property("p")
  private Boolean p;

  @Property("startTime")
  private Double startTime;

  @Property("endTime")
  private Double endTime;

  public FinishedChildLessonData() {}

  public FinishedChildLessonData withId(String id) {
    this.id = id;
    return this;
  }

  public FinishedChildLessonData withScheduleId(String scheduleId) {
    this.scheduleId = scheduleId;
    return this;
  }

  public FinishedChildLessonData withWeek(Double week) {
    this.week = week;
    return this;
  }

  public FinishedChildLessonData withType(int type) {
    this.type = type;
    return this;
  }

  public FinishedChildLessonData withTrainerId(String trainerId) {
    this.trainerId = trainerId;
    return this;
  }

  public FinishedChildLessonData withChildId(String childId) {
    this.childId = childId;
    return this;
  }

  public FinishedChildLessonData withStatus(String status) {
    this.status = status;
    return this;
  }

  public FinishedChildLessonData withScore(int score) {
    this.score = score;
    return this;
  }

  public FinishedChildLessonData withDuration(int duration) {
    this.duration = duration;
    return this;
  }

  public FinishedChildLessonData withSkillId(String skillId) {
    this.skillId = skillId;
    return this;
  }

  public FinishedChildLessonData withLang(String lang) {
    this.lang = lang;
    return this;
  }

  public FinishedChildLessonData withRating(int rating) {
    this.rating = rating;
    return this;
  }

  public FinishedChildLessonData withTrail(Boolean trail) {
    isTrail = trail;
    return this;
  }

  public FinishedChildLessonData withP(Boolean p) {
    this.p = p;
    return this;
  }

  public FinishedChildLessonData withStartTime(Double startTime) {
    this.startTime = startTime;
    return this;
  }

  public FinishedChildLessonData withEndTime(Double endTime) {
    this.endTime = endTime;
    return this;
  }

  public String getId() {
    return id;
  }

  public String getScheduleId() {
    return scheduleId;
  }

  public Double getWeek() {
    return week;
  }

  public int getType() {
    return type;
  }

  public String getTrainerId() {
    return trainerId;
  }

  public String getChildId() {
    return childId;
  }

  public String getStatus() {
    return status;
  }

  public int getScore() {
    return score;
  }

  public int getDuration() {
    return duration;
  }

  public String getSkillId() {
    return skillId;
  }

  public String getLang() {
    return lang;
  }

  public int getRating() {
    return rating;
  }

  public Boolean getTrail() {
    return isTrail;
  }

  public Boolean getP() {
    return p;
  }

  public Double getStartTime() {
    return startTime;
  }

  public Double getEndTime() {
    return endTime;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FinishedChildLessonData that = (FinishedChildLessonData) o;
    return type == that.type
        && score == that.score
        && duration == that.duration
        && rating == that.rating
        && Objects.equals(id, that.id)
        && Objects.equals(scheduleId, that.scheduleId)
        && Objects.equals(week, that.week)
        && Objects.equals(trainerId, that.trainerId)
        && Objects.equals(childId, that.childId)
        && Objects.equals(status, that.status)
        && Objects.equals(skillId, that.skillId)
        && Objects.equals(lang, that.lang)
        && Objects.equals(isTrail, that.isTrail)
        && Objects.equals(p, that.p);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        id,
        scheduleId,
        week,
        type,
        trainerId,
        childId,
        status,
        score,
        duration,
        skillId,
        lang,
        rating,
        isTrail,
        p);
  }

  @Override
  public String toString() {
    return "FinishedChildLessonData{"
        + "id='"
        + id
        + '\''
        + ", scheduleId='"
        + scheduleId
        + '\''
        + ", week="
        + week
        + ", type="
        + type
        + ", trainerId='"
        + trainerId
        + '\''
        + ", childId='"
        + childId
        + '\''
        + ", status='"
        + status
        + '\''
        + ", score="
        + score
        + ", duration="
        + duration
        + ", skillId='"
        + skillId
        + '\''
        + ", lang='"
        + lang
        + '\''
        + ", rating="
        + rating
        + ", isTrail="
        + isTrail
        + ", p="
        + p
        + ", startTime="
        + startTime
        + ", endTime="
        + endTime
        + '}';
  }
}
