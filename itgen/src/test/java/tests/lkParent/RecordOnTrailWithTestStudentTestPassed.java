package tests.lkParent;
/*T-31
 * Ребенок добавлен в дефолтную семью. В админке направлений указано, что на майнкрафт нужен
 * тест. Тест есть в админке с тестами. Ребенок проходит по возрасту на направление. У ребенка есть
 * пройденный тест на направление майнкрафт. На завтра ест свободное занятие. Тренер Настя Бокша
 * ведет майнкрафт. Запистаься на пробное по майнкрафт.
 */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import data.model.schedule.Schedules;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RecordOnTrailWithTestStudentTestPassed extends TestBase {
  private final String period = "18:00 - 20:00";

  @BeforeMethod
  public void ensurePreconditions() {
    data.tests().set2_NewStudentInDefaultFamilyPassedTestOnMinecraft();
    data.skills()
        .set2_MinecraftWithTest(data.skillsService().findBySkillId("21", "ru").getId(),
            "test");
    data.schedules().set3_SingleScheduleWithoutStudent(period, "14");
    data.trainerService().updateField("14", "skills", new String[]{"1", "2", "5", "21"});
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testRecordOnTrailWithTestStudentTestPassed() {
    Schedules before = app.dbschedules().schedules();
    app.lkParentRecord().recordOnTrail(21);
    Schedules after = app.dbschedules().schedules();
    app.check().textElement(By.xpath("//h4"), "Ждем на занятии");

    app.check().equalityOfTwoElements(after.size(), before.size());
    data.schedules().set6_SingleScheduleWithOneStudentRecordOnTrailOnMinecraft(period);
    Schedules afterNew = app.dbschedules().schedules();
    app.check().equalityOfTwoElements(after, afterNew);

  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.postClean().student().tests();
  }
}
