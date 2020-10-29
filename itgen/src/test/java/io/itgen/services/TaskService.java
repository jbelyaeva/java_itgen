package io.itgen.services;

import io.itgen.dao.TaskDao;
import io.itgen.model.LeadData;
import io.itgen.model.StudentData;
import io.itgen.model.tasks.TaskData;

public class TaskService {

  private final TaskDao taskDao = new TaskDao();

  public TaskService() {}

  public TaskData DeleteById(String id) {
    return taskDao.findByIdAndDeleteTask(id);
  }

  public TaskData DeleteById(StudentData student) {
    return taskDao.findByIdAndDeleteTask(student);
  }

  public TaskData DeleteById(LeadData leadNew) {
    return taskDao.findByIdAndDeleteTask(leadNew);
  }

  public void drop() {
    taskDao.drop();
  }

  public void save(TaskData task) {
    taskDao.save(task);
  }

  public TaskData findById(String id) {
    return taskDao.findById(id);
  }

  public void deleteField(String idTask, String nameField) {
    taskDao.deleteField(idTask, nameField);
  }

  public void updateField(String idTask, String nameFiled, String data) {
    taskDao.updateField(idTask, nameFiled, data);
  }
}
