package ru.stqa.pft.itgen.tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import ru.stqa.pft.itgen.model.ParentData;
import ru.stqa.pft.itgen.model.StudentData;

public class FamilyCreationTests extends TestBase {
  public WebDriver wd;

  
  @Test
  public void testFamilyCreation() {
    app.getNavigationHelper().gotoStudents();
    app.getStudentHelper().createFamily();
    app.getStudentHelper().addStudent();
    app.getStudentHelper().addParent();
    app.getStudentHelper().fillStudentForm(new StudentData("Миша", "0Мишин", "М", "01.01.1993", "Армения", "Сватково", "(GMT+05:00) Азия/Ташкент", null, null, null, "89035540414", "sk123", "http:/88888", "89629861121", "44444444444", "tg56", "vk555", "ок34", "ok88", "inst569", null, null));
    app.getStudentHelper().fillFamilyParentForm(new ParentData("Витя", "Витин", "Белиз", "Брест", "(GMT-07:00) Америка/Крестон", "84965404336", "skype99", "789@56.hg", "http", "74994031057", "89035569812", "tele", "fb78", "vk001", "ok458", "inst44"));
    app.getStudentHelper().submitFamilyCreation();

  }
}
