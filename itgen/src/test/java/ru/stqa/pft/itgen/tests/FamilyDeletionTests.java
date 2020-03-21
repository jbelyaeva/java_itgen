package ru.stqa.pft.itgen.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class FamilyDeletionTests extends TestBase {

  @Test
  public void testFamilyDeletion() {
    app.getNavigationHelper().gotoStudents();
    int before = app.getStudentHelper().getStudentCount();
    app.getStudentHelper().selectedStudent();
    app.getStudentHelper().selectedFamily();
    app.getStudentHelper().deleteFamily();
    app.getStudentHelper().assertDeleteSelectedFamily();
    app.getNavigationHelper().gotoStudents();
    int after = app.getStudentHelper().getStudentCount();
    Assert.assertEquals(after, before - 1);
  }
}
