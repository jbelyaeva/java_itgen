package tests.lkParent;
/*T-33
 * Ребенок добавлен в дефолтную семью. В админке направлений указано, что на майнкрафт нужен
 * тест и доступный возраст после 9 лет. Тест есть в админке с тестами. Ребенок не проходит по
 * возрасту (7 лет) на направление. У ребенка есть пройденный тест на направление майнкрафт. На
 * завтра ест свободное занятие. Тренер Настя Бокша ведет майнкрафт. Проверить, что данное
 * направление не отображается в списке направлений.
 */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RecordOnTrailWithTestStudent7YearsTestPassed extends TestBase {

  private final String period = "18:00 - 20:00";

  @BeforeMethod
  public void ensurePreconditions() {
    data.tests().set2_NewStudentInDefaultFamilyPassedTestOnMinecraft();
    data.skills()
        .set3_MinecraftWithTestAfter9Years(data.skillsService().findBySkillId("21", "ru").getId(),
            "test");
    data.schedules().set3_SingleScheduleWithoutStudent(period, "14");
    data.trainerService().updateField("14", "skills", new String[]{"1", "2", "5", "21"});
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testRecordOnTrailWithTestStudent7YearsTestPassed() {
    app.lkParent().reset();
    app.lkParentRecord().btnRecordOnTrail();
    app.check().notFindElement(app.lkParent().getBtnRecordOnTrailOnMinecraft());
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.clean().student().tests();
    data.skills().set2_MinecraftWithTest(data.skillsService().findBySkillId("21", "ru").getId(),
        "test");
  }
}
