package tests.students;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import app.testbase.TestBase;
import data.model.users.StudentData;
import data.model.users.Students;
import data.services.FamilyService;
import data.services.StudentService;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class StudentDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.trFamily().newFamily("studentDelete", false, "txc");

    app.trStudent()
        .NewStudent(
            "studentDelete",
            "Маша",
            "Машина",
            "expert",
            "BL",
            "studentDelete",
            "Europe/Minsk",
            2,
            app.base().DateWithCorrectionDays(-3650),
            "ru",
            "ru",
            "12345678",
            "ru",
            "1",
            2);
  }

  @Test
  public void testStudentDeletion() {
    app.goTo().menuStudents();
    Students before = app.dbstudents().students();
    app.student().selectStudentInListUIById("studentDelete");
    app.student().delete();
    Students after = app.dbstudents().students();
    assertThat(after.size(), equalTo(before.size() - 1));

    for (StudentData student : before) {
      if (student.getId().equals("studentDelete")) {
        assertThat(after, equalTo(before.without(student)));
        return;
      }
    }
    verifyStudentsListInUI();
    app.goTo().menuTasks();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    StudentService studentService = new StudentService();
    studentService.DeleteById("studentDelete");
    FamilyService familyService = new FamilyService();
    familyService.DeleteById("studentDelete");
  }
}
