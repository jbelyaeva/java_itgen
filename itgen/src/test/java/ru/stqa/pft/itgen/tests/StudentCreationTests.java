package ru.stqa.pft.itgen.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.itgen.model.StudentData;

public class StudentCreationTests extends TestBase {

  @Test
  public void testStudentCreation() {
    app.getNavigationHelper().gotoStudents();
    app.getStudentHelper().createFamily();
    app.getStudentHelper().addStudent();
    app.getStudentHelper().fillStudentForm(new StudentData("Миша", "0Мишин", "М", "01.01.1993", "Армения", "Сватково", "(GMT+05:00) Азия/Ташкент", null, null, null, "89035540414", "sk123", "http:/88888", "89629861121", "44444444444", "tg56", "vk555", "ок34", "ok88", "inst569", null, null));
    app.getStudentHelper().submitStudentCreation();
  }
}
