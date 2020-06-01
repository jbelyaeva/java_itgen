package ru.stqa.pft.itgen.model.Schedule;

import dev.morphia.annotations.PostLoad;

import java.util.HashMap;
import java.util.Objects;

public class C {
  private String id;
  private int type;
  private String subject;
  private String s;
  private String lang;
  private Boolean isTrial;
  private Boolean newSubj;
  private int score;

  public C() {
  }


  public C withId(String id) {
    this.id = id;
    return this;
  }

  public C withType(int type) {
    this.type = type;
    return this;
  }

  public C withSubject(String subject) {
    this.subject = subject;
    return this;
  }

  public C withS(String s) {
    this.s = s;
    return this;
  }

  public C withLang(String lang) {
    this.lang = lang;
    return this;
  }

  public C withTrial(Boolean trial) {
    isTrial = trial;
    return this;
  }

  public C withNewSubj(Boolean newSubj) {
    this.newSubj = newSubj;
    return this;
  }

  public C withScore(int score) {
    this.score = score;
    return this;
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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    C c = (C) o;
    return type == c.type &&
            Objects.equals(id, c.id) &&
            Objects.equals(subject, c.subject) &&
            Objects.equals(lang, c.lang) &&
            Objects.equals(isTrial, c.isTrial)&&
            Objects.equals(newSubj, c.newSubj);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, type, subject, lang, isTrial,newSubj);
  }
}
