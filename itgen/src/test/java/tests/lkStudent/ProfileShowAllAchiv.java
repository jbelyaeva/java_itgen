package tests.lkStudent;
/* T-193
Дефолтный ученик. Одна ачивка выполнена, вторая нначата, остальные закрыты
Кейс: открыть профайл через быстрые переходы, посмотреть все ачивкм - отображаются все
 */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ProfileShowAllAchiv extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    data.achievements().set2();
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testProfileShowAllAchiv() {
    app.lkStudent().goToFeed();
    app.lkStudent().btnCloseTutorial();
    app.lkStudent().goInProfile();
    app.lkStudent().btnShowAllAchievements();
    app.check().countElements(app.lkStudent().getAchievementsAll(), 6);
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.clean().achievements();
  }
}
