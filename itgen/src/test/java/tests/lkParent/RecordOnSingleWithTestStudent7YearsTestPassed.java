package tests.lkParent;
/*T-36
 * Ребенок добавлен в дефолтную семью. Вчера было пробное по скрейч. В админке направлений
 * указано, что на майнкрафт нужен тест. Тест есть в админке с тестами. Ребенок не проходит по
 * возрасту на направление. У ребенка есть пройденный тест на направление майнкрафт и завершенное
 * пробное Скрейч. Убедиться, что при записи на платное в выпадающем списке направлений нет
 * Майнкрафт
 */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RecordOnSingleWithTestStudent7YearsTestPassed extends TestBase {

  private final String period = "18:00 - 20:00";

  @BeforeMethod
  public void ensurePreconditions() {
    data.tests().set4_NewStudentInDefaultFamily_AfterTrialByScratch_PassedTestOnMinecraft(period);
    data.skills()
        .set3_MinecraftWithTestAfter9Years(data.skillsService().findBySkillId("21", "ru").getId(),
            "test");
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testRecordOnSingleWithTestStudent7YearsTestPassed() {
    app.lkParent().reset();
    app.lkParentRecord().recordOnSingleOpenSkill();
    app.check().notFindElement(app.lkParent().getDropDownSkillMinecraftInRecord());
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.clean().student().tests().taskAndSchedule();
    data.skills().set2_MinecraftWithTest(data.skillsService().findBySkillId("21", "ru").getId(),
        "test");
  }
}
