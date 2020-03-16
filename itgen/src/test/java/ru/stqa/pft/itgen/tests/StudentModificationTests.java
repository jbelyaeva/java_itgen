package ru.stqa.pft.itgen.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.itgen.model.StudentData;

public class StudentModificationTests extends TestBase {

  @Test
  public void testStudentModification() {
    app.getNavigationHelper().gotoStudents();
    app.getStudentHelper().selectedStudent();
    app.getStudentHelper().modifyStudent();
    app.getStudentHelper().ModifyStudentForm(new StudentData("Вася", "0Васин", "Ж", "30.11.2020","Только планшет, телефон", "Россия", "Пересвет", "(GMT+05:00) Азия/Ташкент", "Русский", "Русский", "1", "55555540414", "sk555", "http:/5555", "99999861121", "99994444444", "tg88", "vk999", "ок777", "ok000", "inst000", "wwwqqqqq777", "hello, Sergey! 8888"));
    app.getStudentHelper().submitStudentModify();
  }
}