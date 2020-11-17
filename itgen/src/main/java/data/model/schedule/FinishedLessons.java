package data.model.schedule;

import com.google.common.collect.ForwardingSet;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class FinishedLessons extends ForwardingSet<FinishedLessonData> {
  private final Set<FinishedLessonData> delegate;

  public FinishedLessons(FinishedLessons finishedLessons) {
    this.delegate = new HashSet<FinishedLessonData>(finishedLessons.delegate);
  }

  public FinishedLessons() { // конструктор без параметров
    this.delegate = new HashSet<FinishedLessonData>();
  }

  public FinishedLessons(Collection<FinishedLessonData> finishedLessons) {
    this.delegate = new HashSet<FinishedLessonData>(finishedLessons);
  }

  public FinishedLessons withAdded(FinishedLessonData finishedLesson) {
    FinishedLessons finishedLessons = new FinishedLessons(this);
    finishedLessons.add(finishedLesson);
    return finishedLessons;
  }

  public FinishedLessons without(FinishedLessonData finishedLesson) {
    FinishedLessons finishedLessons = new FinishedLessons(this);
    finishedLessons.remove(finishedLesson);
    return finishedLessons;
  }

  @Override
  protected Set<FinishedLessonData> delegate() {
    return delegate;
  }
}
