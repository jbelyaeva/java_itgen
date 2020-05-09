package ru.stqa.pft.itgen.services;

import ru.stqa.pft.itgen.dao.StudentDao;
import ru.stqa.pft.itgen.model.StudentData;

public class StudentService {
  private StudentDao studentDao = new StudentDao();

  public StudentService() {
  }

  public void create(StudentData student) {
    studentDao.create(student);
  }
}
