package io.itgen.tests.chat;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import io.itgen.general.RunTestAgain;
import io.itgen.model.chat.ChatMessages;
import io.itgen.model.chat.ChatRooms;
import io.itgen.model.chat.ChatSubscriptions;
import io.itgen.services.ChatMessageService;
import io.itgen.services.ChatRoomService;
import io.itgen.services.ChatSubscriptionService;
import io.itgen.tests.TestBase;
import java.net.MalformedURLException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class ChatSendMessageNewDialogTest extends TestBase {
  ChatRoomService chatRoomService = new ChatRoomService();
  ChatMessageService chatMessageService = new ChatMessageService();
  ChatSubscriptionService chatSubscriptionService = new ChatSubscriptionService();
  String message="Привет";

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testChatSendMessageNewDialog(){

    ChatMessages beforeMessage = app.dbchat().chatMessages();
    ChatRooms beforeRooms = app.dbchat().chatRooms();
    ChatSubscriptions  beforeSubscription = app.dbchat().chatSubscriptions();

    app.chat().sendMessage("Тренер",message);
    String messageGetTrainer = app.chat().getByTrainerMessage("trainer","111111");

    ChatMessages afterMessage = app.dbchat().chatMessages();
    ChatRooms afterRooms = app.dbchat().chatRooms();
    ChatSubscriptions afterSubscription = app.dbchat().chatSubscriptions();

    assertThat(afterMessage.size(), equalTo(beforeMessage.size() + 1));
    assertThat(afterRooms.size(), equalTo(beforeRooms.size() + 1));
    assertThat(afterSubscription.size(), equalTo(beforeSubscription.size() + 2));
    assertThat(message, equalTo(messageGetTrainer));
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    chatRoomService.drop();
    chatMessageService.drop();
    chatSubscriptionService.drop();
  }
}
