package ru.stqa.pft.itgen.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
//  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Slots> slots = new ArrayList<Slots>();
  /**********   Сложное поле Slots (конец)  ********************/

  @ElementCollection(fetch = FetchType.EAGER)
  @Column(name = "finishedSlots")
  private List<FinishedSlots> finishedSlots = new ArrayList<FinishedSlots>();

  @Column(name = "times")
  Times times;

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