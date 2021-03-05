package tests.lkStudent;
/*T-306
 *Завершен урок без материалов. Прогресс бара нет.
 */

import static app.appmanager.ApplicationManager.properties;

import app.testbase.TestBase;
import core.general.RunTestAgain;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ProgressBarFinishedWithoutProjects extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    data.finishedLessonWithProject().set8_YesterdaScratchWasWithoutProjects_StudentDefault();
    app.base().goByHref(app.base().address() + "/login");
    app.student()
        .login(properties.getProperty("web.Login"), properties.getProperty("web.Password"));
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testProgressBarFinishedThreeProjects() {
    app.lkStudent().goInProfile();
    app.check().notFindElement(app.lkStudent().getProgressBar());
    app.lkStudent().goToFeed();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.clean().material().achievements();
    data.defFamily().set19_ChangeDefaultStudentInStart();
    app.base().goByHref(app.base().address() + "/login");
    app.student()
        .login(properties.getProperty("web.Login"), properties.getProperty("web.Password"));
  }
}
