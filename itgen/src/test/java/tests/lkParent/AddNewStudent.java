package tests.lkParent;
/* T-16 */
// к дефолтному родителю и ученику добавляется еще ученик и затем
// удалим этого ученика и расписание в after-методе

import app.testbase.TestBase;
import core.general.RunTestAgain;
import data.model.users.StudentData;
import data.model.users.Students;
import data.provides.LocaleUtilsTestData;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class AddNewStudent extends TestBase {

  StudentData studentClean;

  @Test(dataProvider = "validStudentsFromJson", dataProviderClass = LocaleUtilsTestData.class,
      retryAnalyzer = RunTestAgain.class)
  public void testAddNewStudent(StudentData student) throws InterruptedException {
    Students before = app.dbstudents().students();
    app.lkParent().create(student);
    Thread.sleep(3000); // необходимо, т.к. не успевает сохраниться студент в бд
    Students after = app.dbstudents().students();
    app.check().equalityOfTwoElements(after.size(), before.size() + 1);
    studentClean = app.dbstudents().lastStudent();
    StudentData studentAdd = student.withId(studentClean.getId());
    app.check().equalityOfTwoElements(after, before.withAdded(studentAdd));
  }

  @Test(dataProvider = "noValidLkParentStudentsFromJson", dataProviderClass = LocaleUtilsTestData.class,
      retryAnalyzer = RunTestAgain.class)
  public void testBadAddNewStudent(StudentData student) {
    Students before = app.dbstudents().students();
    app.lkParent().createBad(student);
    Students after = app.dbstudents().students();
    app.check().equalityOfTwoElements(after.size(), before.size());
    app.check().equalityOfTwoElements(after, before);
    studentClean = null;
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    if (studentClean == null) {
      return;
    }
    data.studentService().DeleteById(studentClean);
    data.postClean().taskAndSchedule();
  }
}
