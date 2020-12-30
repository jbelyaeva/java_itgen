package data.model.schedule;

import dev.morphia.annotations.Embedded;
import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.Property;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity("comments")
public class CommentData {
  @Id
  @Property("_id")
  private String id;

  @Property("owner")
  private String owner;

  @Property("target")
  private String target;

  @Property("s")
  private String s;

  @Property("w")
  private Double w;

  @Property("sTime")
  private Double sTime;

  @Property("eTime")
  private Double eTime;

  @Property("skillId")
  private String skillId;

  @Property("createdAt")
  private Date createdAt;

  @Property("text")
  private String text;

  @Property("done")
  private String done;

  @Property("hw")
  private String hw;

  @Property("textForParents")
  private String textForParents;

  @Property("t")
  private String t;

  @Property("topics")
  private String topics;

  @Embedded
  private Grades grades;

  @Embedded
  private List<HwMaterials> hwMaterials = new ArrayList<HwMaterials>();

  @Embedded
  private List<DoneMaterials> doneMaterials = new ArrayList<DoneMaterials>();

  public CommentData() {}

  public CommentData withId(String id) {
    this.id = id;
    return this;
  }

  public CommentData withOwner(String owner) {
    this.owner = owner;
    return this;
  }

  public CommentData withTarget(String target) {
    this.target = target;
    return this;
  }

  public CommentData withS(String s) {
    this.s = s;
    return this;
  }

  public CommentData withW(Double w) {
    this.w = w;
    return this;
  }

  public CommentData withSTime(Double sTime) {
    this.sTime = sTime;
    return this;
  }

  public CommentData withETime(Double eTime) {
    this.eTime = eTime;
    return this;
  }

  public CommentData withSkillId(String skillId) {
    this.skillId = skillId;
    return this;
  }

  public CommentData withCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
    return this;
  }

  public CommentData withText(String text) {
    this.text = text;
    return this;
  }

  public CommentData withDone(String done) {
    this.done = done;
    return this;
  }

  public CommentData withHw(String hw) {
    this.hw = hw;
    return this;
  }

  public CommentData withTextForParents(String textForParents) {
    this.textForParents = textForParents;
    return this;
  }

  public CommentData withT(String t) {
    this.t = t;
    return this;
  }

  public CommentData withTopics(String topics) {
    this.topics = topics;
    return this;
  }

  public CommentData withGrades(Grades grades) {
    this.grades = grades;
    return this;
  }

  public CommentData withHwMaterials(List<HwMaterials> hwMaterials) {
    this.hwMaterials = hwMaterials;
    return this;
  }

  public CommentData withDoneMaterials(List<DoneMaterials> doneMaterials) {
    this.doneMaterials = doneMaterials;
    return this;
  }

  public String getId() {
    return id;
  }

  public String getOwner() {
    return owner;
  }

  public String getTarget() {
    return target;
  }

  public String getS() {
    return s;
  }

  public Double getW() {
    return w;
  }

  public Double getsTime() {
    return sTime;
  }

  public Double geteTime() {
    return eTime;
  }

  public String getSkillId() {
    return skillId;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public String getText() {
    return text;
  }

  public String getDone() {
    return done;
  }

  public String getHw() {
    return hw;
  }

  public String getTextForParents() {
    return textForParents;
  }

  public String getT() {
    return t;
  }

  public String getTopics() {
    return topics;
  }

  public Grades getGrades() {
    return grades;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CommentData that = (CommentData) o;
    return Objects.equals(id, that.id)
        && Objects.equals(owner, that.owner)
        && Objects.equals(target, that.target)
        && Objects.equals(s, that.s)
        && Objects.equals(w, that.w)
        && Objects.equals(sTime, that.sTime)
        && Objects.equals(eTime, that.eTime)
        && Objects.equals(skillId, that.skillId)
        && Objects.equals(text, that.text)
        && Objects.equals(done, that.done)
        && Objects.equals(hw, that.hw)
        && Objects.equals(textForParents, that.textForParents)
        && Objects.equals(t, that.t)
        && Objects.equals(topics, that.topics)
        && Objects.equals(grades, that.grades);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        id,
        owner,
        target,
        s,
        w,
        sTime,
        eTime,
        skillId,
        text,
        done,
        hw,
        textForParents,
        t,
        topics,
        grades);
  }

  @Override
  public String toString() {
    return "CommentData{" +
        "id='" + id + '\'' +
        ", owner='" + owner + '\'' +
        ", target='" + target + '\'' +
        ", s='" + s + '\'' +
        ", w=" + w +
        ", sTime=" + sTime +
        ", eTime=" + eTime +
        ", skillId='" + skillId + '\'' +
        ", createdAt=" + createdAt +
        ", text='" + text + '\'' +
        ", done='" + done + '\'' +
        ", hw='" + hw + '\'' +
        ", textForParents='" + textForParents + '\'' +
        ", finished='" + t + '\'' +
        ", topics='" + topics + '\'' +
        ", grades=" + grades +
        '}';
  }
}
