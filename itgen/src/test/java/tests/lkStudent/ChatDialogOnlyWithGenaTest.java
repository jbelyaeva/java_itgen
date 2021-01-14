package tests.lkStudent;

import static app.appmanager.ApplicationManager.properties;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import app.testbase.TestBase;
import core.general.RunTestAgain;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ChatDialogOnlyWithGenaTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    data.postClean().chat();
    data.defFamily().set21_StartDefaultStudent();
    app.base().goByHref(app.base().address() + "/login");
    app.student()
        .login(properties.getProperty("web.Login"), properties.getProperty("web.Password"));
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testChatDialogOnlyWithGena() {
    String[] dialogs = app.chat().getDialogs();
    assertThat(dialogs.length, equalTo(1));
    assertThat(dialogs[0], equalTo("Айтигеник"));
    app.chat().btnCloseChat();
  }
}
