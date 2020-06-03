package ru.stqa.pft.itgen.model;

import com.google.common.collect.ForwardingSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Workers extends ForwardingSet<WorkerData> {
  private Set<WorkerData> delegate;

  public Workers(Workers workers) {
    this.delegate = new HashSet<WorkerData>(workers.delegate);
  }

  public Workers() { //конструктор без параметров
    this.delegate = new HashSet<WorkerData>();
  }

  public Workers(Collection<WorkerData> workes) {
    this.delegate = new HashSet<WorkerData>(workes);
  }

  public Workers withAdded(WorkerData worker) { //объект в который добавлена группа
    Workers workers = new Workers(this);
    workers.add(worker);
    return workers;
  }

  /* public Workers withFamilyId(String id) {//объекта,в который добавлена группа
     Workers workers = new Workers(this);
     WorkerData workerData = workers.iterator().next().withFamilyId(id);
     return parents;
   }
 */
  public Workers without(WorkerData worker) {//объекта, из которго удалена группа
    Workers workers = new Workers(this);
    workers.remove(worker);
    return workers;
  }

  @Override
  protected Set<WorkerData> delegate() {
    return delegate;
  }
}
