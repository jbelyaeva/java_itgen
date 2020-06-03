package ru.stqa.pft.itgen.services;

import ru.stqa.pft.itgen.dao.StudentDao;
import ru.stqa.pft.itgen.model.StudentData;

public class StudentService {
  private StudentDao studentDao = new StudentDao();

  public StudentService() {
  }

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
}
