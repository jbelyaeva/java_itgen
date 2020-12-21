package tests.lkParent;
/* T-9 */
/* тестовая ситуация: есть дефолтная семья, к которой добавлен ученик записанный на пробное
  Нажать Подготовка к пробному - открылась страница с инструкцией */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class GeneralPageWithInstructionBeforeTrial extends TestBase {
  String period = "18:00 - 20:00";

  @BeforeMethod
  public void ensurePreconditions() {
   data.defFamily().set5_SingleLessonTomorrowTrialWithStudent_StudentAddInDefaultFamily(period);
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testGeneralPageWithInstructionBeforeTrial() {
    app.lkParent().reset();
    app.lkParent().btnPrepare();
    app.check().textElement(app.lkParent().getLabelWaiteOnLessons(), "Ждем на занятии");
    app.check().textElement(app.lkParent().getLabelRecomendationForFirstLesson(),
        "Рекомендации для первого занятия");
    app.lkParent().btnLogo();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.postClean().taskAndSchedule().student();
  }
}
