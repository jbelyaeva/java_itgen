package data.services;

import data.dao.StudentDao;
import data.model.users.StudentData;
import data.model.usersGeneral.Services;
import java.util.Date;

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

  public void updateFieldString(String idStudent, String nameFiled, String data) {
    studentDao.updateFieldString(idStudent, nameFiled, data);
  }

  public void updateFieldServices(String idStudent, String nameFiled, Services data) {
    studentDao.updateFieldServices(idStudent, nameFiled, data);
  }

  public void updateFieldDate(String idStudent, String nameFiled, Date data) {
    studentDao.updateFieldDate(idStudent, nameFiled, data);
  }

  public void deleteField(String idStudent, String nameField) {
    studentDao.deleteField(idStudent, nameField);
  }
}
