package io.itgen.services;

import io.itgen.dao.StudentDao;
import io.itgen.model.StudentData;

public class StudentService {
  private StudentDao studentDao = new StudentDao();

  public StudentService() {}

  public StudentData findByIdAndDelete(StudentData student) {
    return studentDao.findByIdAndDelete(student);
  }

  public StudentData findByIdAndDelete(String id) {
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
}
