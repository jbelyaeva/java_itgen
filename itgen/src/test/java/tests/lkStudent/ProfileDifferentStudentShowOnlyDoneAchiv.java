package tests.lkStudent;
/* T-191
 * Есть сообщество. На него пописаны: дефолтный ребенок и новый студент. Новый студент имеет
 * 4 ачивки (последняя не выполнена)
 * Перейти: лента/сообщество/подписчики/новый студент
 * В открывшимся про файле проверить, что не отображается 4 ачивка
 */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ProfileDifferentStudentShowOnlyDoneAchiv extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    data.community()
        .set9_CommunityScratchWithPost_DefaultStudentSubscriber_SecondStudentSubscriberWith4Achiv();
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testProfileDifferentStudentShowOnlyDoneAchiv() {
    app.lkStudent().btnCloseTutorial();
    app.lkStudent().goToFeed();
    app.lkStudent().selectCommunityScratch();
    app.lkStudent().tabSubscrimers();
    app.lkStudent().selectDifferentStudent("student");
    app.check().countElements(app.lkStudent().getAchievements(), 3);
    app.check().notFindElement(app.lkStudent().getBtnShowAllAchievement());
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.clean().material().achievements().communities().family().student().parent();
  }
}
