package tests.requests;
/* Т-322
 * есть : новый ребенок и родитель
 * есть: заявку на двухчасовое постоянное на Python, к тренеру Вероника
 * Вероника не ведет пайтон
 * зайти к ученику в профиль в таб Заявки - есть индикатор
 * при наведении - "Тренер больше не ведет этл направление"*/

import app.testbase.TestBase;
import core.general.RunTestAgain;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RequestIndicator extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    data.newFamily().set1_FamilyWithStudentAndParent();
    data.requests().set5_requestOnPython2hSingle();
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testRequestRegularNotGreenIfGroupSingle() {
    app.goTo().menuStudents();
    app.student().selectStudentInListUIById("student");
    app.student().tabRequests();
    app.check().findElement(app.request().getIndicator());
    app.request().moveToElement(app.request().getIndicator());
    app.check()
        .textElement(app.request().getIndicatorMessage(), "Тренер больше не ведет это направление");
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.postClean().taskAndSchedule().student().family().parent().requests();
  }
}
