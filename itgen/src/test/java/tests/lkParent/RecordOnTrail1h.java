package tests.lkParent;
/* T-467
 * есть дефолтная семья, к которой добавлен ученик и разовое расписание на завтра в 18.00, на
 * которое нужно записать добавленного ученика
 * в админке направление на скреч стоит пробное 1 час
 */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RecordOnTrail1h extends TestBase {

  private final String period = "18:00 - 20:00";

  @BeforeMethod
  public void ensurePreconditions() {
    data.defFamily().set6_SingleLessonTomorrowWithoutStudent_StudentAddInDefaultFamily(period);
    data.skills()
        .set3_ChangeDurationTrialScratch(data.skillsService().findBySkillId("1", "ru").getId(), 1);
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testRecordOnTrail1h() {
    app.lkParentRecord().recordOnTrail(1);
    //проверяем, что запись на 1 час
    app.check().equalityOfTwoElements(app.lkParentRecord().getDuration(), 1);
    //проверяем, что запись на первый час
    app.check().equalityOfTwoElements(app.lkParentRecord().getDiffHoursStart(period), 0);
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.clean().taskAndSchedule().student();
    data.skills()
        .set3_ChangeDurationTrialScratch(data.skillsService().findBySkillId("1", "ru").getId(), 2);
  }
}
