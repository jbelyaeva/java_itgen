package ru.stqa.pft.itgen.model;

import com.google.common.collect.ForwardingSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Trainers extends ForwardingSet<TrainerData> {
  private Set<TrainerData> delegate;

  public Trainers(Trainers trainers) {
    this.delegate = new HashSet<TrainerData>(trainers.delegate);
  }

  public Trainers() { //конструктор без параметров
    this.delegate = new HashSet<TrainerData>();
  }

  public Trainers(Collection<TrainerData> trainers) {
    this.delegate = new HashSet<TrainerData>(trainers);
  }

  public Trainers withAdded(TrainerData trainer) { //объект в который добавлена группа
    Trainers trainers = new Trainers(this);
    trainers.add(trainer);
    return trainers;
  }

 /* public Workers withFamilyId(String id) {//объекта,в который добавлена группа
    Workers workers = new Workers(this);
    WorkerData workerData = workers.iterator().next().withFamilyId(id);
    return parents;
  }
*/
  public Trainers without(TrainerData trainer) {//объекта, из которго удалена группа
    Trainers trainers = new Trainers(this);
    trainers.remove(trainer);
    return trainers;
  }

  @Override
  protected Set<TrainerData> delegate() {
    return delegate;
  }
}
