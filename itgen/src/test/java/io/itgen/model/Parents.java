package io.itgen.model;

import com.google.common.collect.ForwardingSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Parents extends ForwardingSet<ParentData> {
  private Set<ParentData> delegate;

  public Parents(Parents parents) {
    this.delegate = new HashSet<ParentData>(parents.delegate);
  }

  public Parents() { //конструктор без параметров
    this.delegate = new HashSet<ParentData>();
  }

  public Parents(Collection<ParentData> parents) {
    this.delegate = new HashSet<ParentData>(parents);
  }

  public Parents withAdded(ParentData parent) { //объект в который добавлена группа
    Parents parents = new Parents(this);
    parents.add(parent);
    return parents;
  }

  public Parents withFamilyId(String id) {//объекта,в который добавлена группа
    Parents parents = new Parents(this);
    ParentData parentData = parents.iterator().next().withFamilyId(id);
    return parents;
  }

  public Parents without(ParentData parent) {//объекта, из которго удалена группа
    Parents parents = new Parents(this);
    parents.remove(parent);
    return parents;
  }

  @Override
  protected Set<ParentData> delegate() {
    return delegate;
  }
}
