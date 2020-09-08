package io.itgen.model.schedule;

import com.google.common.collect.ForwardingSet;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class FinishedChildLessons extends ForwardingSet<FinishedChildLessonData> {
  private Set<FinishedChildLessonData> delegate;

  public FinishedChildLessons(FinishedChildLessons finishedChildLessons) {
    this.delegate = new HashSet<FinishedChildLessonData>(finishedChildLessons.delegate);
  }

  public FinishedChildLessons() { //конструктор без параметров
    this.delegate = new HashSet<FinishedChildLessonData>();
  }

  public FinishedChildLessons(Collection<FinishedChildLessonData>  finishedChildLessons) {
    this.delegate = new HashSet<FinishedChildLessonData>(finishedChildLessons);
  }

  public FinishedChildLessons withAdded(FinishedChildLessonData finishedChildLesson) {
    FinishedChildLessons finishedChildLessons = new FinishedChildLessons(this);
    finishedChildLessons.add(finishedChildLesson);
    return finishedChildLessons;
  }

  public FinishedChildLessons without(FinishedChildLessonData finishedChildLesson) {
    FinishedChildLessons finishedChildLessons = new FinishedChildLessons(this);
    finishedChildLessons.remove(finishedChildLesson);
    return finishedChildLessons;
  }

  @Override
  protected Set<FinishedChildLessonData> delegate() {
    return delegate;
  }
}

