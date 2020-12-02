package data.services;

import data.dao.StudentDao;
import data.model.users.StudentData;

public class StudentService {

  private final StudentDao studentDao = new StudentDao();

  public StudentService() {}

  public StudentData DeleteById(StudentData student) {
    return studentDao.findByIdAndDelete(student);
  }

  public StudentData DeleteById(String id) {
    return studentDao.findByIdAndDelete(id);
  }

  public void save(StudentData student) {
    studentDao.save(student);
  }

  public void delete(StudentData student) {
    studentDao.delete(student);
  }

  public StudentData findById(String id) {
    return studentDao.findById(id);
  }

  public <E> void updateField(String idStudent, String nameFiled, E[] data) {
    studentDao.updateField(idStudent, nameFiled, data);
  }

  public void deleteField(String idStudent, String nameField) {
    studentDao.deleteField(idStudent, nameField);
  }
}
