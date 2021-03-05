package tests.requests;
/* Т-323
 * есть : новый ребенок и родитель
 * есть: заявку на двухчасовое постоянное на Scratch, время дефолтное по ui
 * есть: пустая постоянная группа
 * перейти из заявки в запись на занятие*/

import app.testbase.TestBase;
import core.general.RunTestAgain;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RequestGreenTransitionInRecordOnLesson extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    String period = "18:00 - 20:00";
    data.newFamily().set1_FamilyWithStudentAndParent();
    data.requests().set3_requestOnScratch2hRegular();
    data.schedules().set2_RegularScheduleWithoutStudents(period);
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testRequestGreenTransitionInRecordOnLesson() {
    app.base().refresh();
    app.goTo().menuRequests();
    app.request().btnApply();
    app.check().findElement(app.request().getLabelWindowsRecord());
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.clean().taskAndSchedule().student().family().parent().requests();
  }
}
