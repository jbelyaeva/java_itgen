package tests.lkParent;
/* T-123 */
/*В дефолтную семью добавлен ученик  записан на сегодня на пробное по скрейч и на разовое на майнкрафт
 * Завершить оба занятия: первой с Был, второе - Сорвано. Везде проставить дз. Проверить, что в истории
 * под родителем присутствуют оба занятия, если в фильтре выставлено Все. Только майнкрафт - если
 * в фильтре выбрано Майнкрафт
 */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HistoryAllSkill extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    data.finishedLessonWithProject()
        .set3_YesterdayScratchFinishedMinecraftDisrupt_StudentAddDefaultFamily();
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testHistoryAllSkill() {
    app.lkParent().reset();
    app.lkParent().btnShowHistorySecondChild();
    app.check().findElement(app.lkParent().getLabelInHistoryMinecraft());
    app.check().findElement(app.lkParent().getLabelInHistoryScratch());
    app.lkParent().selectSkillInHistory("Minecraft");
    app.check().findElement(app.lkParent().getLabelInHistoryMinecraft());
    app.check().notFindElement(app.lkParent().getLabelInHistoryScratch());
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.clean().finishedLesson().finishedLesson().taskAndSchedule().student();
  }
}
