package data.services;

import data.dao.TaskDao;
import data.model.tasks.TaskData;

public class TaskService {

  private final TaskDao taskDao = new TaskDao();

  public TaskService() {}

  public TaskData DeleteById(String id) {
    return taskDao.deleteById(id);
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
