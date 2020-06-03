package ru.stqa.pft.itgen.model.tasks;

import dev.morphia.annotations.Embedded;

import java.util.Date;
import java.util.Objects;

public class Activity {
  private String uld;
  private Date ts;
  private String t;
  @Embedded
  private D d;

  public Activity() {
  }

  public Activity withUld(String uld) {
    this.uld = uld;
    return this;
  }

  public Activity withTs(Date ts) {
    this.ts = ts;
    return this;
  }

  public Activity withT(String t) {
    this.t = t;
    return this;
  }

  public Activity withD(D d) {
    this.d = d;
    return this;
  }

  public String getUld() {
    return uld;
  }

  public Date getTs() {
    return ts;
  }

  public String getT() {
    return t;
  }

  public D getD() {
    return d;
  }

  @Override
  public String toString() {
    return "Activity{" +
            "uld='" + uld + '\'' +
            ", ts=" + ts +
            ", t='" + t + '\'' +
            ", d=" + d +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Activity activity = (Activity) o;
    return Objects.equals(uld, activity.uld) &&
            Objects.equals(ts, activity.ts) &&
            Objects.equals(t, activity.t);
  }

  @Override
  public int hashCode() {
    return Objects.hash(uld, ts, t);
  }
}
