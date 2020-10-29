package io.itgen.model.requests;

import dev.morphia.annotations.Embedded;
import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.Property;
import io.itgen.model.general.Activity;
import io.itgen.model.general.Comments;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity("requests")
public class RequestData {
  @Id
  @Property("_id")
  private String id;

  @Property("creator")
  private String creator;

  @Property("creatorAt")
  private Date creatorAt;

  @Property("status")
  private String status;

  @Property("childId")
  private String childId;

  @Embedded("comment")
  private List<Comments> comments = new ArrayList<Comments>();

  @Embedded("activity")
  private List<Activity> activity = new ArrayList<Activity>();

  @Property("skill")
  private String skill;

  @Property("duration")
  private int duration;

  @Property("permanent")
  private Boolean permanent;

  @Property("trial")
  private Boolean trial;

  @Embedded("times")
  private List<Times> times = new ArrayList<Times>();

  // getters and setters

  public RequestData withId(String id) {
    this.id = id;
    return this;
  }

  public RequestData withCreator(String creator) {
    this.creator = creator;
    return this;
  }

  public RequestData withCreatorAt(Date creatorAt) {
    this.creatorAt = creatorAt;
    return this;
  }

  public RequestData withStatus(String status) {
    this.status = status;
    return this;
  }

  public RequestData withChildId(String childId) {
    this.childId = childId;
    return this;
  }

  public RequestData withComments(List<Comments> comments) {
    this.comments = comments;
    return this;
  }

  public RequestData withActivity(List<Activity> activity) {
    this.activity = activity;
    return this;
  }

  public RequestData withSkill(String skill) {
    this.skill = skill;
    return this;
  }

  public RequestData withDuration(int duration) {
    this.duration = duration;
    return this;
  }

  public RequestData withPermanent(Boolean permanent) {
    this.permanent = permanent;
    return this;
  }

  public RequestData withTrial(Boolean trial) {
    this.trial = trial;
    return this;
  }

  public RequestData withTimes(List<Times> times) {
    this.times = times;
    return this;
  }

  public String getId() {
    return id;
  }

  public String getCreator() {
    return creator;
  }

  public Date getCreatorAt() {
    return creatorAt;
  }

  public String getStatus() {
    return status;
  }

  public String getChildId() {
    return childId;
  }

  public List<Comments> getComments() {
    return comments;
  }

  public List<Activity> getActivity() {
    return activity;
  }

  public String getSkill() {
    return skill;
  }

  public int getDuration(int i) {
    return duration;
  }

  public Boolean getPermanent() {
    return permanent;
  }

  public Boolean getTrial() {
    return trial;
  }

  public List<Times> getTimes() {
    return times;
  }

  @Override
  public String toString() {
    return "RequestsData{"
        + "id='"
        + id
        + '\''
        + ", creator='"
        + creator
        + '\''
        + ", creatorAt="
        + creatorAt
        + ", status='"
        + status
        + '\''
        + ", childId='"
        + childId
        + '\''
        + ", comments="
        + comments
        + ", activity="
        + activity
        + ", skill='"
        + skill
        + '\''
        + ", duration="
        + duration
        + ", permanent="
        + permanent
        + ", trial="
        + trial
        + ", times="
        + times
        + '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    RequestData that = (RequestData) o;
    return duration == that.duration
        && Objects.equals(id, that.id)
        && Objects.equals(creator, that.creator)
        && Objects.equals(status, that.status)
        && Objects.equals(childId, that.childId)
        && Objects.equals(skill, that.skill)
        && Objects.equals(permanent, that.permanent)
        && Objects.equals(trial, that.trial);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, creator, status, childId, skill, duration, permanent, trial);
  }
}
