package tests.chat;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import app.testbase.TestBase;
import core.general.RunTestAgain;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ChatRoleInterlocutorTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
   data.chat().set3_DialogSuperTrainer("Привет");
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testChatRoleInterlocutor() {
    app.base().refresh();
    app.chat().btnOpenChat();
    assertThat(app.chat().findRoleInterlocutor("23"), equalTo("Дефолтный Тренер (Тр.)"));
    app.chat().btnCloseChat();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.clean().chat();
  }
}
