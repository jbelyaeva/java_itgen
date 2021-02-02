package data.model.achievemets;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.Property;
import java.util.Date;
import java.util.Objects;

@Entity("achievements-users")
public class AchievementsUsersData {

  @Id
  @Property("_id")
  private String id;

  @Property("userId")
  private String userId;

  @Property("type")
  private int type;

  @Property("done")
  private boolean done;

  @Property("startAt")
  private Date startAt;

  @Property("doneAt")
  private Date doneAt;

  public AchievementsUsersData() {
  }

  public AchievementsUsersData withId(String id) {
    this.id = id;
    return this;
  }

  public AchievementsUsersData withUserId(String userId) {
    this.userId = userId;
    return this;
  }

  public AchievementsUsersData withType(int type) {
    this.type = type;
    return this;
  }

  public AchievementsUsersData withDone(boolean done) {
    this.done = done;
    return this;
  }

  public AchievementsUsersData withStartAt(Date startAt) {
    this.startAt = startAt;
    return this;
  }

  public AchievementsUsersData withDoneAt(Date doneAt) {
    this.doneAt = doneAt;
    return this;
  }

  public String getId() {
    return id;
  }

  public String getUserId() {
    return userId;
  }

  public int getType() {
    return type;
  }

  public boolean isDone() {
    return done;
  }

  public Date getStartAt() {
    return startAt;
  }

  public Date getDoneAt() {
    return doneAt;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AchievementsUsersData that = (AchievementsUsersData) o;
    return type == that.type && done == that.done && Objects.equals(id, that.id)
        && Objects.equals(userId, that.userId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, userId, type, done);
  }

  @Override
  public String toString() {
    return "AchievementsUsersData{" +
        "id='" + id + '\'' +
        ", userId='" + userId + '\'' +
        ", type=" + type +
        ", done=" + done +
        ", startAt=" + startAt +
        ", doneAt=" + doneAt +
        '}';
  }
}
