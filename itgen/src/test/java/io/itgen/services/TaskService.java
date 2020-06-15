package io.itgen.services;

import io.itgen.model.LeadData;
import io.itgen.model.TaskData;
import io.itgen.dao.TaskDao;
import io.itgen.model.StudentData;

public class TaskService {
  private TaskDao taskDao = new TaskDao();

  public TaskService() {
  }

  public TaskData findByIdAndDelete(String id) {
    return taskDao.findByIdAndDelete(id);
  }

  public TaskData findByIdAndDelete(StudentData student) {
    return taskDao.findByIdAndDelete(student);
  }

  public void save(TaskData task) {
    taskDao.save(task);
  }

  public TaskData findByIdAndDeleteLead(LeadData leadNew) {return taskDao.findByIdAndDeleteLead(leadNew); }
}
