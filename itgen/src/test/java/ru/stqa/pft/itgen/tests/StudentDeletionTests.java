package ru.stqa.pft.itgen.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class StudentDeletionTests extends TestBase {

  @Test
  public void testStudentDeletion() {
    app.getNavigationHelper().gotoTasks();
    app.getNavigationHelper().gotoStudents();
    int before = app.getStudentHelper().getStudentCount();
    app.getStudentHelper().selectedStudent();
    app.getStudentHelper().deleteStudent();
    app.getStudentHelper().assertDeleteSelectedStudent();
    app.getNavigationHelper().gotoStudents();
    int after = app.getStudentHelper().getStudentCount();
    Assert.assertEquals(after, before - 1);
  }
}
