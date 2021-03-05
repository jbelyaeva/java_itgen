package tests.lkStudent;
/* T-292
 * Есть сообщество. На него пописаны: дефолтный ребенок и новый студент. Новый студент имеет
 * 1 ачику, 1 часы, 1 прогресс-бар
 * Перейти: лента/сообщество/подписчики/новый студент
 * В открывшимся профайле проверить, что отображается информация о себе, ачивка, часы и прогрксс-бар
 */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ProfileDifferentStudent extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    data.community()
        .set7_CommunityScratchWithPost_DefaultStudentSubscriber_SecondStudentSubscriber();
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testProfileDifferentStudent() {
    app.lkStudent().btnCloseTutorial();
    app.lkStudent().goToFeed();
    app.lkStudent().selectCommunityScratch();
    app.lkStudent().tabSubscrimers();
    app.lkStudent().selectDifferentStudent("student");
    app.check()
        .textElement(app.lkStudent().getInformationAboutMyselfInProfile(),
            data.studentService().findById("student").getAbout());
    app.check().countElements(app.lkStudent().getProgressBar(), 1);
    app.check().countElements(app.lkStudent().getAchievements(), 3);
    app.check().countElements(app.lkStudent().getFinishedHours(), 1);
    app.check().notFindElement(app.lkStudent().getBtnShowAllAchievement());
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.clean().material().achievements().communities().family().student().parent();
  }
}
