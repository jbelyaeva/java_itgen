package tests.lkParent;
/* T-49 */
/* Перейти из главной страницы дефолтной семьи на страницу бесплатных занятий. Скопировать ссылку и проверить, что
 * код равен копируемому.
 */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class PageFreeLessonsCheckRefCode extends TestBase {

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testPageFreeLessonsCheckRefCode()
      throws IOException, UnsupportedFlavorException {
    app.lkParent().reset();
    app.check().equalityOfTwoElements(app.lkParent().getRefCode(), "https://itgen.io/?refId=111");
    app.base().refresh();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.postClean().chat();
  }
}
