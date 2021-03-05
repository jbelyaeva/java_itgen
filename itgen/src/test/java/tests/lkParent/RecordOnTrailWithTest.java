package tests.lkParent;
/*T-29
 * Ребенок добавлен в дефолтную семью. В админке направлений указано, что на майнкрафт нужен
 * тест. Тест есть в админке направлений. Ребенок проходит по возрасту на направление. Проверить,
 * что появилась кнопка Пройти тест в окне выбора направлений при записи на пробное.
 */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RecordOnTrailWithTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    data.defFamily().set13_addNewStudentOlder7Years();
    data.skills()
            .set2_MinecraftWithTest(data.skillsService().findBySkillId("21", "ru").getId(), "test");
    data.tests().set1_NewTestForRusMinecraft();
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testRecordOnTrailWithTest() {
    app.lkParent().reset();
    app.lkParentRecord().btnRecordOnTrail();
    app.check()
        .textElement(app.lkParent().getBtnRecordOnTrailOnMinecraft(), "Пройти тест для записи");
    // проверить, что при нажатии на кнопку прйти тест, перекидывает в окно выдачи теста родителем - нет прав!
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.clean().student().tests();
  }
}
