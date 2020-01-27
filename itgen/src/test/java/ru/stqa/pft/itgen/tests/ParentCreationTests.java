package ru.stqa.pft.itgen.tests;

import org.testng.annotations.*;
import ru.stqa.pft.itgen.model.ParentData;

public class ParentCreationTests extends TestBase {

  @Test
  public void testParentCreation() {
    app.getNavigationHelper().gotoStudents();
    app.getStudentHelper().selectedStudent();
    app.getStudentHelper().selectedFamily();
    app.getStudentHelper().addParentInFamily();
    app.getStudentHelper().fillParentForm(new ParentData("Витя", "Витин", null, null, null, "84956669812", "sky", "709@test.ru", "http 123", "89036547810", "89632154578", "tg456", "fb111", "vr008", "ok999", "gghik"));
    app.getStudentHelper().submitParentCreation();
  }
}