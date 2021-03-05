package tests.lkParent;
/**
 * T-158 есть дефолтная семья, к которой добавлен ученик и разовое расписание ИФ на завтра в 18.00
 * (Бокша) Под родителем перейти в запись на пробное для нового ученика Убедиться, что занятие Бокши
 * не отображается для записи
 */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RecordOnIFTrail extends TestBase {

  private final String period = "18:00 - 20:00";

  @BeforeMethod
  public void ensurePreconditions() {
    data.defFamily().set13_addNewStudentOlder7Years();
    data.schedules().set37_SingleIFScheduleWithoutStudent(period, "14");
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testRecordOnIFTrail() {
    app.lkParentRecord().recordOnIFTrail(1);
    app.check()
        .textElement(app.lkParentRecord().getLabelNotFoundLessonsForTrial(),
            "Извините, нет доступных для записи групп. Свяжитесь с Айтигеником, чтобы оставить заявку");
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.clean().taskAndSchedule().student();
  }
}
