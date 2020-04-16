package ru.stqa.pft.itgen.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class StudentDeletionTests extends TestBase {

  @Test
  public void testStudentDeletion() {
    app.goTo().gotoTasks();
    app.goTo().gotoStudents();
    int before = app.students().getStudentCount();
    app.students().selectedStudent();
    app.students().deleteStudent();
    app.students().assertDeleteSelectedStudent();
    app.goTo().gotoStudents();
    int after = app.students().getStudentCount();
    Assert.assertEquals(after, before - 1);
  }
}
