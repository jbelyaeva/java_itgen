package io.itgen.services;

import io.itgen.model.LeadData;
import io.itgen.model.TaskData;
import io.itgen.dao.TaskDao;
import io.itgen.model.StudentData;

public class TaskService {
  private TaskDao taskDao = new TaskDao();

  public TaskService() {}

  public TaskData findByIdAndDeleteTask(String id) {
    return taskDao.findByIdAndDeleteTask(id);
  }

  public TaskData findByIdAndDeleteTask(StudentData student) {
    return taskDao.findByIdAndDeleteTask(student);
  }

  public TaskData findByIdAndDeleteTask(LeadData leadNew) {
    return taskDao.findByIdAndDeleteTask(leadNew);
  }

  public void drop() {
    taskDao.drop();
  }

  public void save(TaskData task) {
    taskDao.save(task);
  }
}
