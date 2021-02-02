package tests.requests;
/* Т-464
 * есть : новый ребенок и родитель
 * есть: заявку на двухчасовое постоянное на Scratch, время дефолтное по ui
 * есть: пустая постоянная группа
 * перейти из заявки в запись на занятие, записать ученика на 2 ч на скрейч - перейти в профиль ученика
 *  - заявка выполнена*/

import app.testbase.TestBase;
import core.general.RunTestAgain;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RequestGreenDoneIfRecordInGroup extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    String period = "18:00 - 20:00";
    data.newFamily().set1_FamilyWithStudentAndParent();
    data.requests().set3_requestOnScratch2hRegular();
    data.schedules().set2_RegularScheduleWithoutStudents(period);
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testRequestGreenDoneIfRecordInGroup() {
    app.base().refresh();
    app.goTo().menuRequests();
    app.request().btnApply();
    app.windowSchedule().recordStudentOn2hRegularFromRequest();
    app.goTo().menuStudents();
    app.student().selectStudentInListUIById("student");
    app.student().tabRequests();
    app.check().findElement(app.request().getRequestInStack());
    app.check().textElement(app.request().getStatusRequest(), "Выполнена");
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.postClean().taskAndSchedule().student().family().parent().requests();
  }
}
