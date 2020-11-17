package data.model.general;

import dev.morphia.annotations.Embedded;
import java.util.Date;
import java.util.Objects;

public class Activity {
  private String uId;
  private Date ts;
  private String t;
  @Embedded private D d;
  private String s;
  private Double w;
  private Boolean f;

  public Activity() {}

  public Activity withUId(String uId) {
    this.uId = uId;
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

  public Activity withS(String s) {
    this.s = s;
    return this;
  }

  public Activity withW(Double w) {
    this.w = w;
    return this;
  }
  public Activity withF(Boolean f) {
    this.f = f;
    return this;
  }

  public String getUId() {
    return uId;
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

  public String getS() {
    return s;
  }

  public Double getW() {
    return w;
  }

  public Boolean getF() {
    return f;
  }

  @Override
  public String toString() {
    return "Activity{"
        + "uId='"
        + uId
        + '\''
        + ", ts="
        + ts
        + ", t='"
        + t
        + '\''
        + ", d="
        + d
        + '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Activity activity = (Activity) o;
    return Objects.equals(uId, activity.uId) && Objects.equals(t, activity.t);
  }

  @Override
  public int hashCode() {
    return Objects.hash(uId, t);
  }
}
