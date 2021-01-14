package tests.lkParent;
/*T-34
 * Ребенок добавлен в дефолтную семью. Вчера было пробное по скрейч. В админке направлений
 * указано, что на майнкрафт нужен тест. Тест есть в админке с тестами. Ребенок проходит по возрасту
 * на направление. У ребенка есть НЕ пройденный тест на направление майнкрафт. На завтра ест
 * свободное занятие. Тренер Настя Бокша ведет майнкрафт. Запистаься на разовое по майнкрафт.
 */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RecordOnSingleWithTestStudentTestFailed extends TestBase {

  private final String period = "18:00 - 20:00";

  @BeforeMethod
  public void ensurePreconditions() {
    data.tests().set5_NewStudentInDefaultFamily_AfterTrialByScratch_FailedTestOnMinecraft(period);
    data.skills()
        .set2_MinecraftWithTest(data.skillsService().findBySkillId("21", "ru").getId(),
            "test");
    data.schedules().set3_SingleScheduleWithoutStudent(period);
    data.trainerService().updateField("14", "skills", new String[]{"1", "2", "5", "21"});
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testRecordOnSingleWithTestStudentTestFailed() {
    app.lkParent().reset();
    app.lkParentRecord().recordOnSingleOnNewSkillWithFailedTest("Minecraft");
    app.check()
        .textElement(app.lkParent().getLabelNotAllowedRecordOnTestingSkillWithoutTest(),
            "Тест пройден неуспешно. Попробуйте другое направление");
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.postClean().student().tests().taskAndSchedule();
  }
}
