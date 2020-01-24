package ru.stqa.pft.itgen.tests;

import org.testng.annotations.*;
import ru.stqa.pft.itgen.model.ParentData;

public class ParentCreationTest extends TestBase {

  @Test
  public void testParentCreation() {
    app.getNavigationHelper().gotoStudents();
    app.getStudentHelper().selectedStudent();
    app.getStudentHelper().selectedFamily();
    app.getStudentHelper().addParent();
    app.getStudentHelper().fillParentForm(new ParentData("Витя", "Витин", "111111111111", "skype", "101@test.ru", "https://web.chat2desk.com/chat/my?dialogID=9346556", "111111111111", "111111111111", "telegram", "fb", "vk", "ok", "inst"));
    app.getStudentHelper().submitParentCreation();
  }
}