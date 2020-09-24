package io.itgen.model.tasks;

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

  @Property("leadUser")
  private String linkLead;

  @Property("dueDate")
  private Date dueDate;

  @Property("dueDateSort")
  private double dueDateSort;

  @Property("dueDateWithTime")
  private Date dueDateWithTime;

  @Embedded("lesson")
  private Lesson lesson;

  @Property("creator")
  private String creator;

  @Property("assignee")
  private String assignee;

  @Property("text")
  private String text;

  @Property("priority")
  private int priority;

  private String dateUi;

  private String timeUi;

  private String userUi;

  private String dueDateTimeUi;

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

  public TaskData withLinkLead(String linkLead) {
    this.linkLead = linkLead;
    return this;
  }

  public TaskData withDueDate(Date dueDate) {
    this.dueDate = dueDate;
    return this;
  }

  public TaskData withDueDateSort(double dueDateSort) {
    this.dueDateSort = dueDateSort;
    return this;
  }

  public TaskData withDueDateWithTime(Date dateWithTime) {
    this.dueDateWithTime = dateWithTime;
    return this;
  }

  public TaskData withLesson(Lesson lesson) {
    this.lesson = lesson;
    return this;
  }

  public TaskData withCreator (String creator) {
    this.creator = creator;
    return this;
  }

  public TaskData withAssignee(String assignee) {
    this.assignee = assignee;
    return this;
  }

  public TaskData withText(String text) {
    this.text = text;
    return this;
  }

  public TaskData withPriority(int priority) {
    this.priority = priority;
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

  public String getLinkLead() {
    return linkLead;
  }

  public Date getDueDate() {
    return dueDate;
  }

  public double getDueDateSort() {
    return dueDateSort;
  }

  public Date getDueDateWithTime() {
    return dueDateWithTime;
  }

  public String getCreator() {
    return creator;
  }

  public String getAssignee() {
    return assignee;
  }

  public Lesson getLesson() {
    return lesson;
  }

  public String getText() {
    return text;
  }

  public int getPriority() {
    return priority;
  }

  public String getDateUi() {
    return dateUi;
  }

  public String getTimeUi() {
    return timeUi;
  }

  public String getUserUi() {
    return userUi;
  }

  public String getDueDateTimeUi() {
    return dueDateTimeUi;
  }

  @Override
  public String toString() {
    return "TaskData{" +
            "id='" + id +
            ", creator=" + creator +
            ", assignee=" + assignee +
            ", text='" + text +
            ", priority='" + priority +
            ", t='" + t +
            ", createAt=" + createAt +
            ", status='" + status +
            ", watchers=" + watchers +
            ", comments=" + comments +
            ", activity=" + activity +
            ", linkUser='" + linkUser +
            ", dueDate=" + dueDate +
            ", dueDateSort=" + dueDateSort +
            ", dueDateWithTime=" + dueDateWithTime +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    TaskData taskData = (TaskData) o;
    return Objects.equals(id, taskData.id) &&
            Objects.equals(t, taskData.t) &&
            Objects.equals(creator, taskData.creator) &&
            Objects.equals(assignee, taskData.assignee) &&
            Objects.equals(text, taskData.text) &&
            Objects.equals(priority, taskData.priority) &&
            Objects.equals(status, taskData.status) &&
            Objects.equals(watchers, taskData.watchers) &&
            Objects.equals(comments, taskData.comments) &&
            Objects.equals(activity, taskData.activity) &&
            Objects.equals(linkUser, taskData.linkUser);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, t, creator, assignee, text, priority, status, watchers, comments, activity, linkUser);
  }
}