package ru.stqa.pft.itgen.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.itgen.model.ParentData;

public class ParentModificationTests extends TestBase {

  @Test
  public void testParentModification() {
    app.getNavigationHelper().gotoStudents();
    app.getStudentHelper().selectedStudent();
    app.getStudentHelper().selectedFamily();
    app.getStudentHelper().selectedParent();
    app.getStudentHelper().modifyParent();
    app.getStudentHelper().ModifyParentForm(new ParentData("Вова", "Ласточкин", "Венгрия", "Высоково", "(GMT-06:00) Америка/Бойла, Северная Дакота", "Английский", "84958887856", "домик", null, "ггггггг", "89995632129", "74965477823", "telega", "бук", "контакт", "одноклассники", "стаг", "444ггггг", "кккккккк"));
    app.getStudentHelper().submitParentModify();
  }
}
