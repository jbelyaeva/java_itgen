package tests.lkStudent;
/* T-192
 * Есть сообщество. На него пописаны: дефолтный ребенок и новый студент. Новый студент имеет
 * 4 ачивки, 1 часы, 1 прогресс-бар
 * Перейти: лента/сообщество/подписчики/новый студент
 * В открывшимся про файле проверить, что отображается информация о себе, ачивки, часы и прогрксс-бар
 */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ProfileDifferentStudentWith4Achiv extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    data.community()
        .set8_CommunityScratchWithPost_DefaultStudentSubscriber_SecondStudentSubscriberWith4Achiv();
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testProfileDifferentStudentWith4Achiv() {
    app.lkStudent().btnCloseTutorial();
    app.lkStudent().goToFeed();
    app.lkStudent().selectCommunityScratch();
    app.lkStudent().tabSubscrimers();
    app.lkStudent().selectDifferentStudent("student");
    app.check()
        .textElement(app.lkStudent().getInformationAboutMyselfInProfile(),
            data.studentService().findById("student").getAbout());
    app.check().countElements(app.lkStudent().getAchievements(), 3);
    app.check().countElements(app.lkStudent().getFinishedHours(), 1);
    app.check().findElement(app.lkStudent().getBtnShowAllAchievement());
    app.lkStudent().btnShowAllAchievements();
    app.check().countElements(app.lkStudent().getAchievements(), 4);
    app.check().notFindElement(app.lkStudent().getBtnShowAllAchievement());
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.clean().material().achievements().communities().family().student().parent();
  }
}
