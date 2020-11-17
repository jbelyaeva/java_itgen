package data.model.tasks;

import com.google.common.collect.ForwardingSet;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Tasks extends ForwardingSet<TaskData> {
  private final Set<TaskData> delegate;

  public Tasks(Tasks tasks) {
    this.delegate = new HashSet<TaskData>(tasks.delegate);
  }

  public Tasks() { // конструктор без параметров
    this.delegate = new HashSet<TaskData>();
  }

  public Tasks(Collection<TaskData> tasks) {
    this.delegate = new HashSet<TaskData>(tasks);
  }

  public Tasks withAdded(TaskData task) { // объект в который добавлена группа
    Tasks tasks = new Tasks(this);
    tasks.add(task);
    return tasks;
  }

  public Tasks withStudentId(String id) { // объекта,в который добавлена группа
    Tasks tasks = new Tasks(this);
    TaskData taskData = tasks.iterator().next().withLinkUser(id);
    return tasks;
  }

  public Tasks without(TaskData task) { // объекта, из которго удалена группа
    Tasks tasks = new Tasks(this);
    tasks.remove(task);
    return tasks;
  }

  @Override
  protected Set<TaskData> delegate() {
    return delegate;
  }
}
