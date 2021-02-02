package tests.requests;
/* Т-319
 * есть : новый ребенок и родитель
 * есть: заявку на двухчасовое постоянное на Scratch, время дефолтное по ui
 * есть: пустая постоянная группа
 * eсть: пустая разовая группа
 * Записать ученика в разовую группу. Перейти в профиль ученика таб Заявки. Заявка зеленая. Не вполнена.*/

import app.testbase.TestBase;
import core.general.RunTestAgain;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RequestGreenNotDoneIfRecordInOtherGroup extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    String period = "18:00 - 20:00";
    data.newFamily().set1_FamilyWithStudentAndParent();
    data.requests().set3_requestOnScratch2hRegular();
    data.schedules().set2_RegularScheduleWithoutStudents(period);
    data.schedules().se29_SingleScheduleWithoutStudent(period);
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testRequestGreenNotDoneIfRecordInOtherGroup() throws InterruptedException {
    app.goTo().menuSchedule();
    app.schedule().recordStudentOn2h("Олег Олегов", "schedule");
    app.goTo().menuStudents();
    app.student().selectStudentInListUIById("student");
    app.student().tabRequests();
    app.check().findElement(app.request().getRequestGreenInStack());
    app.check().textElement(app.request().getStatusRequest(), "Не выполнена");
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.postClean().taskAndSchedule().student().family().parent().requests();
  }
}
