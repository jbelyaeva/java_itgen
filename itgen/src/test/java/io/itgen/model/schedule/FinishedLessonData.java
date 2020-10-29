package io.itgen.model.schedule;

import dev.morphia.annotations.Embedded;
import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.Property;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity("finished-lessons")
public class FinishedLessonData {
  @Id
  @Property("_id")
  private String id;

  @Property("scheduleId")
  private String scheduleId;

  @Property("week")
  private Double week;

  @Property("trainerId")
  private String trainerId;

  @Property("startTime")
  private Double startTime;

  @Property("endTime")
  private Double endTime;

  @Property("type")
  private int type;

  @Embedded("childs")
  private List<Childs> childs = new ArrayList<Childs>();

  @Property("startedAt")
  private Double startedAt;

  @Property("finishedAt")
  private Double fibishedAt;

  public FinishedLessonData() {}

  public FinishedLessonData withId(String id) {
    this.id = id;
    return this;
  }

  public FinishedLessonData withScheduleId(String scheduleId) {
    this.scheduleId = scheduleId;
    return this;
  }

  public FinishedLessonData withWeek(Double week) {
    this.week = week;
    return this;
  }

  public FinishedLessonData withTrainerId(String trainerId) {
    this.trainerId = trainerId;
    return this;
  }

  public FinishedLessonData withStartTime(Double startTime) {
    this.startTime = startTime;
    return this;
  }

  public FinishedLessonData withEndTime(Double endTime) {
    this.endTime = endTime;
    return this;
  }

  public FinishedLessonData withType(int type) {
    this.type = type;
    return this;
  }

  public FinishedLessonData withChilds(List<Childs> childs) {
    this.childs = childs;
    return this;
  }

  public FinishedLessonData withStartedAt(Double startedAt) {
    this.startedAt = startedAt;
    return this;
  }

  public FinishedLessonData withFibishedAt(Double fibishedAt) {
    this.fibishedAt = fibishedAt;
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

  public String getTrainerId() {
    return trainerId;
  }

  public Double getStartTime() {
    return startTime;
  }

  public Double getEndTime() {
    return endTime;
  }

  public int getType() {
    return type;
  }

  public List<Childs> getChilds() {
    return childs;
  }

  public Double getStartedAt() {
    return startedAt;
  }

  public Double getFibishedAt() {
    return fibishedAt;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FinishedLessonData that = (FinishedLessonData) o;
    return type == that.type
        && Objects.equals(id, that.id)
        && Objects.equals(scheduleId, that.scheduleId)
        && Objects.equals(week, that.week)
        && Objects.equals(trainerId, that.trainerId)
        && Objects.equals(startTime, that.startTime)
        && Objects.equals(endTime, that.endTime)
        && Objects.equals(childs, that.childs)
        && Objects.equals(startedAt, that.startedAt)
        && Objects.equals(fibishedAt, that.fibishedAt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        id, scheduleId, week, trainerId, startTime, endTime, type, childs, startedAt, fibishedAt);
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
        + ", trainerId='"
        + trainerId
        + '\''
        + ", startTime="
        + startTime
        + ", endTime="
        + endTime
        + ", type="
        + type
        + ", childs="
        + childs
        + ", startedAt="
        + startedAt
        + ", fibishedAt="
        + fibishedAt
        + '}';
  }
}
