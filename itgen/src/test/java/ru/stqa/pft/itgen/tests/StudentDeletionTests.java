package ru.stqa.pft.itgen.tests;

import org.testng.annotations.Test;

public class StudentDeletionTests extends TestBase {

  @Test
  public void testStudentDeletion() {
    app.getNavigationHelper().gotoStudents();
    app.getStudentHelper().selectedStudent();
    app.getStudentHelper().deleteStudent();
    app.getStudentHelper().assertDeleteSelectedStudent();
  }
}
