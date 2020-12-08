package tests.lkParent;
/* T-49 */
/* Перейти из главной страницы дефолтной семьи на страницу бесплатных занятий. Перейти на страницу фейсбука,
 * нажав иконку фейсбука.
 */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import org.testng.annotations.Test;

public class PageFreeLessonsFacebook extends TestBase {

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testPageFreeLessonsFacebook() throws InterruptedException {
    app.lkParent().reset();
    app.check().equalityOfTwoElements(app.lkParent().goToFacebook(), "www.facebook.com");
    app.base().refresh();
  }
}
