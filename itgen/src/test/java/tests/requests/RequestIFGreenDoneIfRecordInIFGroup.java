package tests.requests;
/* Т-466
 * есть : новый ребенок и родитель
 * есть: заявку на пробное на ИФ на Scratch, время дефолтное по ui
 * есть: пустая ИФ группа
 * перейти из заявки в запись на занятие, записать ученика пробное на скрейч - перейти в профиль ученика
 *  - заявка выполнена*/

import app.testbase.TestBase;
import core.general.RunTestAgain;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RequestIFGreenDoneIfRecordInIFGroup extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    String period = "18:00 - 20:00";
    data.newFamily().set1_FamilyWithStudentAndParent();
    data.requests().set11_requestOnIFPythonTrial();
    data.requestService().deleteField("newRequest", "trainerGender");
    data.schedules().set31_SingleIFScheduleWithoutStudents(period);
    data.skills()
        .set4_ChangeDurationTrialPython(data.skillsService().findBySkillId("2", "ru").getId(), 1);
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testRequestIFGreenDoneIfRecordInIFGroup() {
    app.base().refresh();
    app.goTo().menuRequests();
    app.request().btnApply();
    app.windowSchedule().recordStudentOnIFFromRequest();
    app.goTo().menuStudents();
    app.student().selectStudentInListUIById("student");
    app.student().tabRequests();
    app.check().findElement(app.request().getRequestInStack());
    app.check().textElement(app.request().getStatusRequest(), "Выполнена");
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.postClean().taskAndSchedule().student().family().parent().requests();
    data.skills()
        .set4_ChangeDurationTrialPython(data.skillsService().findBySkillId("2", "ru").getId(), 2);
  }
}
