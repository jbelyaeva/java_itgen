package ru.stqa.pft.itgen.model;

import javax.persistence.Embeddable;

@Embeddable
public class C {
  private String id;
  private int type;
  private String subject;
  private String s;
  private String lang;
  private Boolean isTrial;
  private int score;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public int getType() {
    return type;
  }

  public void setType(int type) {
    this.type = type;
  }

  public String getSubject() {
    return subject;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  public String getS() {
    return s;
  }

  public void setS(String s) {
    this.s = s;
  }

  public String getLang() {
    return lang;
  }

  public void setLang(String lang) {
    this.lang = lang;
  }

  public Boolean getTrial() {
    return isTrial;
  }

  public void setTrial(Boolean trial) {
    isTrial = trial;
  }

  public int getScore() {
    return score;
  }

  public void setScore(int score) {
    this.score = score;
  }

  @Override
  public String toString() {
    return "C{" +
            "id='" + id + '\'' +
            ", type=" + type +
            ", subject='" + subject + '\'' +
            ", s='" + s + '\'' +
            ", lang='" + lang + '\'' +
            ", isTrial=" + isTrial +
            ", score=" + score +
            '}';
  }
}
