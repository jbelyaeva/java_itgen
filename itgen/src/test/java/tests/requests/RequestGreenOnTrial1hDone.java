package tests.requests;
/* Т-445
 * есть семья : новый ребенок и родитель
 * в админке в скейче рус стоит длительность1 ч
 * есть заявка на пробное 1 ч на скрейч
 * есть пустое разовое занятие
 * перейти в профиль ученика/заявки/записаться на занятие через заявки
 * проверить что заявка в профиле выполнена а в общем стеке заявки нет */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RequestGreenOnTrial1hDone extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    String period = "18:00 - 20:00";
    data.newFamily().set1_FamilyWithStudentAndParent();
    data.skills()
        .set3_ChangeDurationTrialScratch(data.skillsService().findBySkillId("1", "ru").getId(), 1);
    data.requests().set2_requestOnScratch1hTrial("newRequest");
    data.schedules().set3_SingleScheduleWithoutStudent(period, "14");
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testRequestGreenOnTrial1hDone() {
    app.base().refresh();
    app.goTo().menuRequests();
    app.request().btnApply();
    app.windowSchedule().recordStudentFromRequest();
    app.goTo().menuStudents();
    app.student().selectStudentInListUIById("student");
    app.student().tabRequests();
    app.check().findElement(app.request().getRequestInStack());
    app.check().textElement(app.request().getStatusRequest(), "Выполнена");
    app.goTo().menuRequests();
    app.check().notFindElement(app.request().getRequestInStack());
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.skills()
        .set3_ChangeDurationTrialScratch(data.skillsService().findBySkillId("1", "ru").getId(), 2);
    data.clean().taskAndSchedule().student().family().parent().requests();
  }
}
