package ru.stqa.pft.itgen.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.itgen.model.StudentData;

public class StudentCreationTests extends TestBase {

  @Test
  public void testStudentCreation() {
    app.getNavigationHelper().gotoStudents();
    app.getStudentHelper().createFamily();
    app.getStudentHelper().addStudent();
    app.getStudentHelper().fillStudentForm(new StudentData("Миша", "0Мишин", "Ж", "11.11.2022", "Бангладеш", "Пересвет", "(GMT+05:00) Азия/Ташкент", "111111111111", "skype", "https://web.chat2desk.com/chat/my?dialogID=9346556", "111111111111", "111111111111", "telega", "fb", "vk", "ok", "inst"));
    app.getStudentHelper().submitStudentCreation();
  }
}
