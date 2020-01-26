package ru.stqa.pft.itgen.tests;

import org.testng.annotations.Test;

public class FamilyDeletionTests extends TestBase {

  @Test
  public void testFamilyDeletion() {
    app.getNavigationHelper().gotoStudents();
    app.getStudentHelper().selectedStudent();
    app.getStudentHelper().selectedFamily();
    app.getStudentHelper().deleteFamily();
    app.getStudentHelper().assertDeleteSelectedFamily();
  }
}
