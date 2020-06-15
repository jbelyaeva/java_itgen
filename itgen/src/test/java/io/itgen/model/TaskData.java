package io.itgen.model;

import dev.morphia.annotations.Embedded;
import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.Property;
import io.itgen.model.tasks.Activity;
import io.itgen.model.tasks.Comments;
import io.itgen.model.tasks.Lesson;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity("tasks")
public class TaskData {
  @Id
  @Property("_id")
  private String id;

  @Property("t")
  private String t;

  @Property("createAt")
  private Date createAt;

  @Property("status")
  private String status;

  @Property("watchers")
  private List<String> watchers = new ArrayList<>();

  @Embedded("comments")
  private List<Comments> comments = new ArrayList<Comments>();

  @Embedded("activity")
  private List<Activity> activity = new ArrayList<Activity>();

  @Property("linkUser")
  private String linkUser;

  @Property("dueDate")
  private Date dueDate;

  @Property("dueDateSort")
  private Date dueDateSort;

  @Embedded("lesson")
  private Lesson lesson;

  public TaskData() {
  }

  public TaskData withId(String id) {
    this.id = id;
    return this;
  }

  public TaskData withT(String t) {
    this.t = t;
    return this;
  }

  public TaskData withCreateAt(Date createAt) {
    this.createAt = createAt;
    return this;
  }

  public TaskData withStatus(String status) {
    this.status = status;
    return this;
  }

  public TaskData withWatchers(List<String> watchers) {
    this.watchers = watchers;
    return this;
  }

  public TaskData withComments(List<Comments> comments) {
    this.comments = comments;
    return this;
  }

  public TaskData withActivity(List<Activity> activity) {
    this.activity = activity;
    return this;
  }

  public TaskData withLinkUser(String linkUser) {
    this.linkUser = linkUser;
    return this;
  }

  public TaskData withDueDate(Date dueDate) {
    this.dueDate = dueDate;
    return this;
  }

  public TaskData withDueDateSort(Date dueDateSort) {
    this.dueDateSort = dueDateSort;
    return this;
  }

  public TaskData withLesson(Lesson lesson) {
    this.lesson = lesson;
    return this;
  }

  public String getId() {
    return id;
  }

  public String getT() {
    return t;
  }

  public Date getCreateAt() {
    return createAt;
  }

  public String getStatus() {
    return status;
  }

  public List<String> getWatchers() {
    return watchers;
  }

  public List<Comments> getComments() {
    return comments;
  }

  public List<Activity> getActivity() {
    return activity;
  }

  public String getLinkUser() {
    return linkUser;
  }

  public Date getDueDate() {
    return dueDate;
  }

  public Date getDueDateSort() {
    return dueDateSort;
  }

  public Lesson getLesson() {
    return lesson;
  }

  @Override
  public String toString() {
    return "TaskData{" +
            "id='" + id + '\'' +
            ", t='" + t + '\'' +
            ", createAt=" + createAt +
            ", status='" + status + '\'' +
            ", watchers=" + watchers +
            ", comments=" + comments +
            ", activity=" + activity +
            ", linkUser='" + linkUser + '\'' +
            ", dueDate=" + dueDate +
            ", dueDateSort=" + dueDateSort +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    TaskData taskData = (TaskData) o;
    return Objects.equals(id, taskData.id) &&
            Objects.equals(t, taskData.t) &&
            Objects.equals(createAt, taskData.createAt) &&
            Objects.equals(status, taskData.status) &&
            Objects.equals(watchers, taskData.watchers) &&
            Objects.equals(comments, taskData.comments) &&
            Objects.equals(activity, taskData.activity) &&
            Objects.equals(linkUser, taskData.linkUser);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, t, createAt, status, watchers, comments, activity, linkUser);
  }
}