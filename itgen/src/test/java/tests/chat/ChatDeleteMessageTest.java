package tests.chat;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import app.testbase.TestBase;
import core.general.RunTestAgain;
import data.model.chat.ChatMessages;
import data.model.chat.ChatRooms;
import data.model.chat.ChatSubscriptions;
import data.services.ChatMessageService;
import data.services.ChatRoomService;
import data.services.ChatSubscriptionService;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class ChatDeleteMessageTest extends TestBase {

  ChatRoomService chatRoomService = new ChatRoomService();
  ChatMessageService chatMessageService = new ChatMessageService();
  ChatSubscriptionService chatSubscriptionService = new ChatSubscriptionService();
  String message = "Привет";

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testChatDeleteMessage() {
    ChatMessages beforeMessage = app.dbchat().chatMessages();
    ChatRooms beforeRooms = app.dbchat().chatRooms();
    ChatSubscriptions beforeSubscription = app.dbchat().chatSubscriptions();
    app.chat().sendMessage("Тренер", message);
    app.chat().deleteMessage("23");
    //  Boolean messageGetTrainer = app.chat().getByTrainerDeletedMessageFromAdmin("trainer", "111111");

    ChatMessages afterMessage = app.dbchat().chatMessages();
    ChatRooms afterRooms = app.dbchat().chatRooms();
    ChatSubscriptions afterSubscription = app.dbchat().chatSubscriptions();

    assertThat(afterMessage.size(), equalTo(beforeMessage.size() + 1));
    assertThat(afterRooms.size(), equalTo(beforeRooms.size() + 1));
    assertThat(afterSubscription.size(), equalTo(beforeSubscription.size() + 2));
    //  assertThat(messageGetTrainer, equalTo(false));
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    chatRoomService.drop();
    chatMessageService.drop();
    chatSubscriptionService.drop();
  }
}
