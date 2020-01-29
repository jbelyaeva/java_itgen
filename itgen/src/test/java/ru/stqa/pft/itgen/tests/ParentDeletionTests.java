package ru.stqa.pft.itgen.tests;

import org.testng.annotations.Test;

public class ParentDeletionTests extends TestBase {

  @Test
  public void testParentDeletion() {
    app.getNavigationHelper().gotoStudents();
    app.getStudentHelper().selectedStudent();
    app.getStudentHelper().selectedFamily();
    app.getStudentHelper().selectedParent();
    app.getStudentHelper().deleteParent();
    app.getStudentHelper().assertDeleteSelectedParent();
  }

}
