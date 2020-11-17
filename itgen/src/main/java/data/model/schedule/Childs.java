package data.model.schedule;

import java.util.Objects;

public class Childs {
  private String id;
  private String status;
  private int score;
  private int duration;
  private String skillId;
  private String lang;
  private int rating;
  private Boolean isTrail;
  private Boolean p;
  private Double startTime;
  private Double endTime;

  public Childs withId(String id) {
    this.id = id;
    return this;
  }

  public Childs withStatus(String status) {
    this.status = status;
    return this;
  }

  public Childs withScore(int score) {
    this.score = score;
    return this;
  }

  public Childs withDuration(int duration) {
    this.duration = duration;
    return this;
  }

  public Childs withSkillId(String skillId) {
    this.skillId = skillId;
    return this;
  }

  public Childs withLang(String lang) {
    this.lang = lang;
    return this;
  }

  public Childs withRating(int rating) {
    this.rating = rating;
    return this;
  }

  public Childs withTrail(Boolean trail) {
    isTrail = trail;
    return this;
  }

  public Childs withP(Boolean p) {
    this.p = p;
    return this;
  }

  public Childs withStartTime(Double startTime) {
    this.startTime = startTime;
    return this;
  }

  public Childs withEndTime(Double endTime) {
    this.endTime = endTime;
    return this;
  }

  public String getId() {
    return id;
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
    Childs childs = (Childs) o;
    return score == childs.score
        && duration == childs.duration
        && rating == childs.rating
        && Objects.equals(id, childs.id)
        && Objects.equals(status, childs.status)
        && Objects.equals(skillId, childs.skillId)
        && Objects.equals(lang, childs.lang)
        && Objects.equals(isTrail, childs.isTrail)
        && Objects.equals(p, childs.p)
        && Objects.equals(startTime, childs.startTime)
        && Objects.equals(endTime, childs.endTime);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        id, status, score, duration, skillId, lang, rating, isTrail, p, startTime, endTime);
  }

  @Override
  public String toString() {
    return "Childs{"
        + "id='"
        + id
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
