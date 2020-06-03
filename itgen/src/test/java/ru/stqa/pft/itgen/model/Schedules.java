package ru.stqa.pft.itgen.model;

import com.google.common.collect.ForwardingSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Schedules extends ForwardingSet<ScheduleData> {
  private Set<ScheduleData> delegate;

  public Schedules(Schedules schedules) {
    this.delegate = new HashSet<ScheduleData>(schedules.delegate);
  }

  public Schedules() { //конструктор без параметров
    this.delegate = new HashSet<ScheduleData>();
  }

  public Schedules(Collection<ScheduleData> schedules) {
    this.delegate = new HashSet<ScheduleData>(schedules);
  }

  public Schedules withAdded(ScheduleData schedule) {
    Schedules schedules = new Schedules(this);
    schedules.add(schedule);
    return schedules;
  }

  /* public Students withFamilyId(String id) {
     Students students = new Students(this);
     StudentData studentData = students.iterator().next().withFamilyId(id);
     return students;
   }*/
  public Schedules without(ScheduleData schedule) {
    Schedules schedules = new Schedules(this);
    schedules.remove(schedule);
    return schedules;
  }

  @Override
  protected Set<ScheduleData> delegate() {
    return delegate;
  }
}

