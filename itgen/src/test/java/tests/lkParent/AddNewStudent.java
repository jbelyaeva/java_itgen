package tests.lkParent;
/* T-16 */
// к дефолтному родителю и ученику добавляется еще ученик и затем
// удалим этого ученика и расписание в after-методе

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import app.testbase.TestBase;
import core.general.RunTestAgain;
import data.model.users.StudentData;
import data.model.users.Students;
import data.provides.LocaleUtilsTestData;
import data.services.StudentService;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class AddNewStudent extends TestBase {

  StudentService studentService = new StudentService();
  StudentData studentClean;

  @Test(dataProvider = "validStudentsFromJson", dataProviderClass = LocaleUtilsTestData.class,
      retryAnalyzer = RunTestAgain.class)
  public void testAddNewStudent(StudentData student) throws InterruptedException {
    Students before = app.dbstudents().students();
    app.lkParent().create(student);
    Thread.sleep(3000); // необходимо, т.к. не успевает сохраниться студент в бд
    Students after = app.dbstudents().students();
    assertThat(after.size(), equalTo(before.size() + 1));
    studentClean = app.dbstudents().lastStudent();
    StudentData studentAdd = student.withId(studentClean.getId());
    assertThat(after, equalTo(before.withAdded(studentAdd)));
  }

  @Test(dataProvider = "noValidLkParentStudentsFromJson", dataProviderClass = LocaleUtilsTestData.class,
      retryAnalyzer = RunTestAgain.class)
  public void testBadAddNewStudent(StudentData student) {
    Students before = app.dbstudents().students();
    app.lkParent().createBad(student);
    Students after = app.dbstudents().students();
    assertThat(after.size(), equalTo(before.size()));
    assertThat(after, equalTo(before));
    studentClean = null;
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    if (studentClean == null) {
      return;
    }
    studentService.DeleteById(studentClean);
    app.postClean().dropTaskAndSchedule();
  }
}
