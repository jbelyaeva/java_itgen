package ru.stqa.pft.itgen.model;

import com.google.common.collect.ForwardingSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Students extends ForwardingSet<StudentData> {
  private Set<StudentData> delegate;

  public Students(Students students) {
    this.delegate = new HashSet<StudentData>(students.delegate);
  }

  public Students() { //конструктор без параметров
    this.delegate = new HashSet<StudentData>();
  }

  public Students(Collection<StudentData> students) {
    this.delegate = new HashSet<StudentData>(students);
  }

  public Students withAdded(StudentData student) { //объект в который добавлена группа
    Students students = new Students(this);
    students.add(student);
    return students;
  }

  public Students withFamilyId(String id) {//объекта,в который добавлена группа
    Students students = new Students(this);
    StudentData studentData = students.iterator().next().withFamilyId(id);
    return students;
  }

  public Students without(StudentData student) {//объекта, из которго удалена группа
    Students students = new Students(this);
    students.remove(student);
    return students;
  }

  @Override
  protected Set<StudentData> delegate() {
    return delegate;
  }
}
