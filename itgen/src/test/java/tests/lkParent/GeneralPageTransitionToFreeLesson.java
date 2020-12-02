package tests.lkParent;
/* T-13 */
/*
 * Перейти из главной страницы в Бесплатные занятия
 */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import org.testng.annotations.Test;

public class GeneralPageTransitionToFreeLesson extends TestBase {

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testGeneralPageTransitionToFreeLesson() {
    app.lkParent().reset();
    app.lkParent().clickByFreeLesson();
    app.check().findElement(app.lkParent().buttonShareHref());
    app.check().findElement(app.lkParent().sectionSocialNetworks());
    app.check().textElement(app.lkParent().labelFreeLessonsHeader(),
        "Как получить бесплатные занятия");
    app.lkParent().btnLogo();
  }

}
