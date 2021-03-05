package tests.students;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import app.testbase.TestBase;
import core.general.RunTestAgain;
import data.model.users.StudentData;
import data.model.users.Students;
import data.provides.LocaleUtilsTestData;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class StudentModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    data.newFamily().set7_FamilyWithStudent();
  }

  @Test(dataProvider = "validModifyStudentsFromJson", dataProviderClass = LocaleUtilsTestData.class,
      retryAnalyzer = RunTestAgain.class)
  public void testStudentModification(StudentData student) {
    app.goTo().menuStudents();
    Students before = app.dbstudents().students();
    app.student().selectStudentInListUIById("newStudent");
    app.student().modify(student);
    Students after = app.dbstudents().students();
    assertThat(after.size(), equalTo(before.size()));

    for (StudentData studentModify : before) { // найти в списке "до" родителя с таким id
      if (studentModify.getId().equals("newStudent")) {
        StudentData studentAdd = student.withId(studentModify.getId());
        assertThat(after, equalTo(before.without(studentModify).withAdded(studentAdd)));
        return;
      }
    }

    verifyStudentsListInUI();
    app.goTo().menuTasks();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.clean().family().student();
  }
}
