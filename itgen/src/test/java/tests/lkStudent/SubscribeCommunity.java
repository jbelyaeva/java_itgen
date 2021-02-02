package tests.lkStudent;
/* T-263
 * Кейс: создано новое сообщестов  без тегов, зайти под студентом в таб Все и
 * подписаться на это сообщество. Убедиться, что кнопка изменилась с Подписаться на Отписаться*/

import app.testbase.TestBase;
import core.general.RunTestAgain;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SubscribeCommunity extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    data.postClean().communities();
    data.community().set6_NewCommunity();
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testSubscribeCommunity() {
    app.lkStudent().btnCommunities();
    app.lkStudent().subscribeOnCommunity();
    app.check().findElement(app.lkStudent().getBtnUnsubscribe());
    app.lkStudent().goToFeed();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.postClean().communities();
  }
}
