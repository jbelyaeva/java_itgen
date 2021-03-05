package tests.lkParent;
/*T-37
 * Ребенок добавлен в дефолтную семью. В админке направлений указано, что на майнкрафт нужен
 * тест. Тест есть в админке тестов. Ребенок проходит по возрасту на направление. У ребенка есть
 * заваленный тест на направление майнкрафт. Запистаься на пробное по майнкрафт нельзя: нет кнопки
 * записаться, тображается результат теста.
 */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RecordOnTrailWithTestStudentTestFailed extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    data.tests().set3_NewStudentInDefaultFamilyFailedTestOnMinecraft();
    data.skills()
        .set2_MinecraftWithTest(data.skillsService().findBySkillId("21", "ru").getId(),
            "test");
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testRecordOnTrailWithTestStudentTestFailed() {
    app.lkParent().reset();
    app.lkParentRecord().btnRecordOnTrail();
    app.check().notFindElement(app.lkParent().getBtnRecordOnTrailOnMinecraft());
    app.check().textElement(app.lkParent().getLabelResultTestPart1(), "Тест пройден на");
    app.check().textElement(app.lkParent().getLabelResultTestPart2(), "1/5");
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.clean().student().tests();
  }
}
