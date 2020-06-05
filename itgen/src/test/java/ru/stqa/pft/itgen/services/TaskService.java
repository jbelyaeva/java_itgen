package ru.stqa.pft.itgen.services;

import ru.stqa.pft.itgen.dao.TaskDao;
import ru.stqa.pft.itgen.model.LeadData;
import ru.stqa.pft.itgen.model.StudentData;
import ru.stqa.pft.itgen.model.TaskData;

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