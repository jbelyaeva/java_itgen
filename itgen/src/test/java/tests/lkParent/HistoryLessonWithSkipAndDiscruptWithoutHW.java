package tests.lkParent;
/* T-127 */
/*В дефолтную семью добавлен ученик  записан на сегодня на пробное по скрейч и на разовое на майнкрафт
 * Завершить оба занятия: первой с Не был, второе - Сорвано без дз. Проверить, что в истории
 * под родителем отсутствуют оба занятия.
 */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HistoryLessonWithSkipAndDiscruptWithoutHW extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    data.finishedLessonWithProject()
        .set4_YesterdayScratchSkippedMinecraftDisrupt_StudentAddDefaultFamily();
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testHistoryLessonWithSkipAndDiscruptWithoutHW() {
    app.lkParent().reset();
    app.lkParent().btnShowHistorySecondChild();
    app.check()
        .notFindElement(app.lkParent().getLabelInHistoryMinecraft());
    app.check().notFindElement(app.lkParent().getLabelInHistoryScratch());
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.defFamily().set13_addNewStudentOlder7Years();
    data.postClean().finishedLesson().finishedLesson().taskAndSchedule().student();
  }
}
