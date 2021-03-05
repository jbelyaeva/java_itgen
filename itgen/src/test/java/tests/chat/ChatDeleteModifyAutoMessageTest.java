package tests.chat;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import app.testbase.TestBase;
import core.general.RunTestAgain;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/*автосообщение родителю при саморегистрации нельзя удалить под админом*/

public class ChatDeleteModifyAutoMessageTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
  data.chat().set4_DialogAutoMessageParent(); //Деф родитель
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testChatDeleteModifyAutoMessage() throws InterruptedException {
    Thread.sleep(2000);
    app.chat().btnOpenChat();
    assertThat(app.chat().deleteAutoMessage("22"), equalTo(false));
    app.chat().btnCloseChat();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.clean().chat();
  }
}
