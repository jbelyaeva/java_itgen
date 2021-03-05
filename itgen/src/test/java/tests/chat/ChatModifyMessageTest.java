package tests.chat;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import app.testbase.TestBase;
import core.general.RunTestAgain;
import data.model.chat.ChatMessages;
import data.model.chat.ChatRooms;
import data.model.chat.ChatSubscriptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ChatModifyMessageTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
   data.chat().set3_1_DialogTrainerSuper("привет");
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testChatModifyMessage() {
    String messageNew = "Пока";
    ChatMessages beforeMessage = app.dbchat().chatMessages();
    ChatRooms beforeRooms = app.dbchat().chatRooms();
    ChatSubscriptions beforeSubscription = app.dbchat().chatSubscriptions();

    app.chat().modifyMessageByAdmin("23", messageNew);

    ChatMessages afterMessage = app.dbchat().chatMessages();
    ChatRooms afterRooms = app.dbchat().chatRooms();
    ChatSubscriptions afterSubscription = app.dbchat().chatSubscriptions();

    assertThat(afterMessage.size(), equalTo(beforeMessage.size()));
    assertThat(afterRooms.size(), equalTo(beforeRooms.size()));
    assertThat(afterSubscription.size(), equalTo(beforeSubscription.size()));
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.clean().chat();
  }
}
