package ru.stqa.pft.itgen.tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import ru.stqa.pft.itgen.model.ParentData;
import ru.stqa.pft.itgen.model.StudentData;

public class FamilyCreationTests extends TestBase {
  public WebDriver wd;


  //Тест не работает
  @Test
  public void testFamilyCreation() {
    app.getNavigationHelper().gotoStudents();
    app.getStudentHelper().createFamily();
    app.getStudentHelper().addStudent();
    app.getStudentHelper().addParent();
    app.getStudentHelper().fillStudentForm(new StudentData("Миша", "0Мишин", "Ж", "11112022", "Бангладеш", "Пересвет", "(GMT+05:00) Азия/Ташкент", "111111111111", "skype", "https://web.chat2desk.com/chat/my?dialogID=9346556", "111111111111", "111111111111", "telega", "fb", "vk", "ok", "inst"));
    app.getStudentHelper().fillFamilyParentForm(new ParentData("Витя", "Витин", "Белиз", "Брест", "(GMT-07:00) Америка/Крестон", "84965404336", "skype99", "789@56.hg", "http", "74994031057", "89035569812", "tele", "fb78", "vk001", "ok458", "inst44"));
    app.getStudentHelper().submitFamilyCreation();

  }
}
