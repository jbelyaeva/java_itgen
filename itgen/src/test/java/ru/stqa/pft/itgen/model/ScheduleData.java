package ru.stqa.pft.itgen.model;

import dev.morphia.annotations.Embedded;
import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.Property;
import ru.stqa.pft.itgen.model.Schedule.Slots;
import ru.stqa.pft.itgen.model.Schedule.Times;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity("schedule")
public class ScheduleData {
  @Id
  @Property("_id")
  private String id;

  @Property("ver")
  private Integer ver;

  @Property("fromDate")
  private Double fromDate;

  @Embedded("slots")
  private List<Slots> slots = new ArrayList<Slots>();

  @Embedded
  private Times times;

  @Property("skypeId")
  private String skypeId;

  @Property("oneTime")
  private Boolean oneTime;

  public ScheduleData() {
  }

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

  public ScheduleData withTimes(Times times) {
    this.times = times;
    return this;
  }

  public ScheduleData withSkypeId(String skypeId) {
    this.skypeId = skypeId;
    return this;
  }

  public ScheduleData withOneTime(Boolean oneTime) {
    this.oneTime = oneTime;
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

  public Times getTimes() {
    return times;
  }

  public String getSkypeId() {
    return skypeId;
  }

  public Boolean getOneTime() {
    return oneTime;
  }

  @Override
  public String toString() {
    return "ScheduleData{" +
            "id='" + id + '\'' +
            ", ver=" + ver +
            ", fromDate=" + fromDate +
            ", slots=" + slots +
            ", times=" + times +
            ", skypeId='" + skypeId + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ScheduleData that = (ScheduleData) o;
    return Objects.equals(id, that.id) &&
            Objects.equals(fromDate, that.fromDate) &&
            Objects.equals(slots, that.slots) &&
            Objects.equals(times, that.times) &&
            Objects.equals(skypeId, that.skypeId) &&
            Objects.equals(oneTime, that.oneTime);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, fromDate, slots, times, skypeId, oneTime);
  }
}