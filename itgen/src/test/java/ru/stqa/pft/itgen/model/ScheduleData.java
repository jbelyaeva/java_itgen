package ru.stqa.pft.itgen.model;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "schedule")
public class ScheduleData {
  @Id
  @Column(name = "_id")
  private String id;

  @Column(name = "ver")
  private Integer ver;

  @Column(name = "fromDate")
  private Double fromDate;

  /**********   Сложное поле Slots (начало)  ********************/

  @ElementCollection(fetch = FetchType.EAGER)
  @Column(name = "slots")
  private List<Slots> slots = new ArrayList<Slots>();

  @Embeddable
  public static class Slots {
    private Double w;
    private String id;
    @Type(type = "class")
    private List<C> c = new ArrayList<>();
    @Embeddable
    public static class C {
      private String id;
      private int type;
      private String subject;
      private String s;
      private String lang;
      private Boolean isTrial;
      private int score;
    }

//    @Type(type = "class")
//    @Column(name = "st")
    @Embedded
    private ST st;

    @Embeddable
    public static class ST {
      public Double s;
      public Double e;

      public Double getS() {
        return s;
      }

      public void setS(Double s) {
        this.s = s;
      }

      public Double getE() {
        return e;
      }

      public void setE(Double e) {
        this.e = e;
      }

      @Override
      public String toString() {
        return "ST{" +
                "s=" + s +
                ", e=" + e +
                '}';
      }
    }

    public Double getW() {
      return w;
    }

    public void setW(Double w) {
      this.w = w;
    }

    public String getId() {
      return id;
    }

    public void setId(String id) {
      this.id = id;
    }

    public List<C> getC() {
      return c;
    }

    public void setC(List<C> c) {
      this.c = c;
    }

    public ST getSt() {
      return st;
    }

    public Slots withSt(ST st) {
      this.st = st;
      return this;
    }

    @Override
    public String toString() {
      return "Slots{" +
              "w=" + w +
              ", id='" + id + '\'' +
              ", c=" + c +
              ", st=" + st +
              '}';
    }
  }

  /**********   Сложное поле Slots (конец)  ********************/

  @ElementCollection(fetch = FetchType.EAGER)
  @Column(name = "finishedSlots")
  private List<FinishedSlots> finishedSlots = new ArrayList<FinishedSlots>();

  @Embeddable
  public static class FinishedSlots {
    private String w; // поля исправить по факту
  }

  @Type(type = "class")
  @Column(name = "times")
  Times times;

  @Embeddable
  public static class Times {
    Integer start;
    Integer end;

    @Override
    public String toString() {
      return "Times{" +
              "start=" + start +
              ", end=" + end +
              '}';
    }
  }

  @Column(name = "skypeId")
  private String skypeId;

  public ScheduleData withId(String id) {
    this.id = id;
    return this;
  }

  public ScheduleData withVer(Integer ver) {
    this.ver = ver;
    return this;
  }

  public ScheduleData withFromDate(Double fromDate) {
    this.fromDate = fromDate;
    return this;
  }

  public ScheduleData withSlots(List<Slots> slots) {
    this.slots = slots;
    return this;
  }

  public ScheduleData withFinishedSlots(List<FinishedSlots> finishedSlots) {
    this.finishedSlots = finishedSlots;
    return this;
  }

  public ScheduleData withTimes(Times times) {
    this.times = times;
    return this;
  }

  public ScheduleData withSkypeId(String skypeId) {
    this.skypeId = skypeId;
    return this;
  }

  public String getId() {
    return id;
  }

  public Integer getVer() {
    return ver;
  }

  public Double getFromDate() {
    return fromDate;
  }

  public List<Slots> getSlots() {
    return slots;
  }

  public List<FinishedSlots> getFinishedSlots() {
    return finishedSlots;
  }

  public Times getTimes() {
    return times;
  }

  public String getSkypeId() {
    return skypeId;
  }

  @Override
  public String toString() {
    return "ScheduleData{" +
            "id='" + id + '\'' +
            ", ver=" + ver +
            ", fromDate=" + fromDate +
            ", slots=" + slots +
            ", finishedSlots=" + finishedSlots +
            ", times=" + times +
            ", skypeId='" + skypeId + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ScheduleData that = (ScheduleData) o;
    return Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}