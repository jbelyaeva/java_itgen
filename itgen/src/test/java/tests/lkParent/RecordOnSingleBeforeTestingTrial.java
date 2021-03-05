package tests.lkParent;
/*T-35
 * Ребенок добавлен в дефолтную семью. Вчера было пробное по майнкрафт. В админке направлений
 * указано, что на майнкрафт нужен тест. Тест есть в админке с тестами. Ребенок проходит по возрасту
 * на направление. Ребенок прошел тест на направление майнкрафт. На завтра ест свободное занятие.
 * Тренер Настя Бокша ведет майнкрафт. Запистаься на разовое по майнкрафт.
 */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import data.model.schedule.Schedules;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RecordOnSingleBeforeTestingTrial extends TestBase {

  private final String period = "18:00 - 20:00";

  @BeforeMethod
  public void ensurePreconditions() {
    data.tests().set6_NewStudentInDefaultFamily_AfterTrialByMinecraft_PassedTestOnMinecraft(period);
    data.skills()
        .set2_MinecraftWithTest(data.skillsService().findBySkillId("21", "ru").getId(),
            "test");
    data.schedules().set3_SingleScheduleWithoutStudent(period, "14");
    data.trainerService().updateField("14", "skills", new String[]{"1", "2", "5", "21"});
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testRecordOnSingleWithTestStudentTestPassed() {
    app.lkParent().reset();
    Schedules before = app.dbschedules().schedules();
    app.lkParentRecord().recordOnSingleOnSkill("Minecraft");
    Schedules after = app.dbschedules().schedules();

    app.check().textElement(By.xpath("//h2"), "Подтверждение записи");
    app.check().equalityOfTwoElements(after.size(), before.size());
    data.schedules().set7_SingleScheduleWithOneStudentRecordOnSingleOnMinecraft(period);
    Schedules afterNew = app.dbschedules().schedules();
    app.check().equalityOfTwoElements(after, afterNew);
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.clean().student().tests().taskAndSchedule();
  }
}
