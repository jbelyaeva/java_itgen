package tests.lkStudent;
/*T-276
 * Начальные данные: дефолтный ребенок c подпиской на сообщество.
 * Зайти в таб Лена и  убедится в отсутвии кнопки Меню с удалить/изменить
 */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FeedCantDeletePost extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    data.community().set4_CommunityScratchWithPost_StudentSubscriber("21");
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testFeedCantDeletePost() {
    app.lkStudent().goToFeed();
    app.check().notFindElement(app.lkStudent().getMenuInPostCommunity());
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.clean().communities();
  }
}
