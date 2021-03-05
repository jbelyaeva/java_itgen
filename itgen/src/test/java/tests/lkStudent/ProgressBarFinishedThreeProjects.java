package tests.lkStudent;
/*T-305
 *Есть Выполненные материалы по трем направлениям. Зайти в профайл и проверить, что
 * 1. Есть кнопка Посмотреть Все
 * 2. После ее нажатия 3 прогресс-бара
 * 3. Кнопка Посмотреть все исчезла
 */

import static app.appmanager.ApplicationManager.properties;

import app.testbase.TestBase;
import core.general.RunTestAgain;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ProgressBarFinishedThreeProjects extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    data.finishedLessonWithProject()
        .set7_ProgressBarFOrDefaultStudent_3ProjecsScratchMinecraftPython();
    app.base().goByHref(app.base().address() + "/login");
    app.student()
        .login(properties.getProperty("web.Login"), properties.getProperty("web.Password"));
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testProgressBarFinishedThreeProjects() {
    app.lkStudent().goInProfile();
    app.lkStudent().btnShowAllInProgressBar();
    app.check().countElements(app.lkStudent().getProgressBar(), 3);
    app.check().notFindElement(app.lkStudent().getBtnShowAllProgressBar());
    app.lkStudent().goToFeed();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.clean().material().achievements();
    data.defFamily().set19_ChangeDefaultStudentInStart();
  }
}
