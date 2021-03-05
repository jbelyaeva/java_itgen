package tests.lkStudent;
/* T-264
 * Кейс: создано новое сообщестов (в ensurePreconditions()) без тегов,
 * дефолтный студент подписан на сообщество. Отписаться от сообщества. Убедиться,
 * что кнопка изменилась с Отписаться на Подписаться */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class UnsubscribeCommunity extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    data.clean().communities();
    data.community().set4_CommunityScratchWithPost_StudentSubscriber("21");
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testUnsubscribeCommunity() {
    app.lkStudent().btnCommunities();
    app.lkStudent().unsubscribeOnCommunity();
    app.check().findElement(app.lkStudent().getBtnSubscribe());
    app.lkStudent().goToFeed();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.clean().communities();
  }
}
