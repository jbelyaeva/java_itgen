package tests.students;
/*T-308
 *Есть Выполненные материалы по трем направлениям. Зайти в профиль ученика и проверить, что
 * 1. Есть кнопка Посмотреть Все
 * 2. После ее нажатия 3 прогресс-бара
 * 3. Кнопка Посмотреть все исчезла
 */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class StudentTabHistoryProgressBar extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    data.finishedLessonWithProject()
        .set7_ProgressBarFOrDefaultStudent_3ProjecsScratchMinecraftPython();
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testStudentTabHistoryProgressBar() {
    app.goTo().menuStudents();
    app.student().selectStudentInListUIById("21");
    app.student().tabHistory();
    app.lkStudent().btnShowAllInProgressBar();
    app.check().countElements(app.lkStudent().getProgressBar(), 3);
    app.check().notFindElement(app.lkStudent().getBtnShowAllProgressBar());
    app.goTo().menuTasks();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.defFamily().set19_ChangeDefaultStudentInStart();
    data.postClean().material().achievements();
  }
}
