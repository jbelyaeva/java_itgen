package tests.chat;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import app.testbase.TestBase;
import core.general.RunTestAgain;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ChatIndicatorTest extends TestBase {


  @BeforeMethod
  public void ensurePreconditions() {
    data.chat().set3_DialogSuperTrainer("Привет");
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testChatIndicator() {
    assertThat(app.chat().findIndicatorMessage(), equalTo(true));
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.clean().chat();
  }
}
