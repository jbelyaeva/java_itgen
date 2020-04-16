package ru.stqa.pft.itgen.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class FamilyDeletionTests extends TestBase {

  @Test
  public void testFamilyDeletion() {
    app.goTo().gotoTasks();
    app.goTo().gotoStudents();
    int before = app.students().getStudentCount();
    app.students().selectedStudent();
    app.students().selectedFamily();
    app.students().deleteFamily();
    app.students().assertDeleteSelectedFamily();
    app.goTo().gotoStudents();
    int after = app.students().getStudentCount();
    Assert.assertEquals(after, before - 1);
  }
}
