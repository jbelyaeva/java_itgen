package ru.stqa.pft.itgen.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @Type(type = "class")
    private Set<ST> st = new HashSet<>();

    @Embeddable
    public static class ST {
      Double s;
      Double e;
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
  private Set<Times> times = new HashSet<>();

  @Embeddable
  public static class Times {
    Integer start;
    Integer end;
  }

  @Column(name = "skypeId")
  private String skypeId;
}