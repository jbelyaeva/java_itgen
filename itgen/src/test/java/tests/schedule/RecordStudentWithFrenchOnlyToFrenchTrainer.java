package tests.schedule;
/* T-383
 * Есть новая французская семья с ребенком. Есть разовое пустое занятие на сегодня 21:00. Тренер ведет на русском
 * и французском.
 *  Записать через поп-ап Запись на занятие на это занятие нового ученика
 */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import data.model.schedule.Schedules;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RecordStudentWithFrenchOnlyToFrenchTrainer extends TestBase {

  private final String period = "21:00 - 23:00";

  @BeforeMethod
  public void ensurePreconditions() {
    data.newFamily().set3_FrFamilyWithStudentAndParent();
    data.schedules().se29_SingleScheduleWithoutStudent(period);
    data.trainerService().updateField("14", "langs", new String[]{"ru", "fr"});
    data.skillsService()
        .updateField(data.skillsService().findBySkillId("1", "en").getId(), "lang", "fr");
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testRecordStudentWithFrenchOnlyToFrenchTrainer() throws InterruptedException {
    String name = "Машина Маша";
    app.goTo().menuSchedule();
    Schedules before = app.dbschedules().schedules();
    app.schedule().recordStudentOn2h(name, "schedule");
    Schedules after = app.dbschedules().schedules();

    app.check().equalityOfTwoElements(after.size(), before.size());
    data.schedules().set33_TodaySingleScheduleWithFrenchStudent(period, "schedule");
    Schedules afterNew = app.dbschedules().schedules();
    app.check().equalityOfTwoElements(after, afterNew);
    app.check().findElement(app.schedule().getStudentOnLessonInSchedule());
    app.goTo().menuTasks();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.clean().student().taskAndSchedule().family().parent();
    data.trainerService().updateField("14", "langs", new String[]{"ru"});
    String idSkill = data.skillsService().findBySkillId("1", "fr").getId();
    data.skillsService()
        .updateField(data.skillsService().findBySkillId("1", "fr").getId(), "lang", "en");
  }
}
