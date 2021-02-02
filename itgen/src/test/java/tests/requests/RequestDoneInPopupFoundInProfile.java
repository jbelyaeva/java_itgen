package tests.requests;
/* Т-321
 * есть : новый ребенок и родитель
 * есть: заявку на двухчасовое постоянное на Scratch, время дефолтное по ui
 * есть: пустая постоянная группа
 * перейти: меню Заяви/выбрать заявку/перейти в поп-ап (откроется при клике на заявку)/моготочие/выполнить
 * перейти: меню Ученики/ выбрать Олег Олегов/таб Заявки
 * проверить, что отображается 1 заявка в статусе Выполнена*/

import app.testbase.TestBase;
import core.general.RunTestAgain;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RequestDoneInPopupFoundInProfile extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    String period = "18:00 - 20:00";
    data.newFamily().set1_FamilyWithStudentAndParent();
    data.requests().set3_requestOnScratch2hRegular();
    data.schedules().set2_RegularScheduleWithoutStudents(period);
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testRequestDoneInPopupFoundInProfile() {
    app.base().refresh();
    app.goTo().menuRequests();
    app.request().selectGreenRequest();
    app.request().btnPoint();
    app.request().btnDoneRequest();
    app.request().btnClosePopup();
    app.check().notFindElement(app.request().getRequestInStack());
    app.check().notFindElement(app.request().getRequestGreenInStack());
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
